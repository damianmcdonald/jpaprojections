package com.github.damianmcdonald.jpaprojections;

import com.github.damianmcdonald.jpaprojections.dao.EntityDao;
import com.github.damianmcdonald.jpaprojections.dao.EntityManagerProvider;
import com.github.damianmcdonald.jpaprojections.dao.TeamDao;
import com.github.damianmcdonald.jpaprojections.model.Team;
import com.github.damianmcdonald.jpaprojections.sync.SyncAction;
import com.github.damianmcdonald.jpaprojections.sync.SyncTrack;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class SimulateApp {

    private static SyncTrack syncTrack;
    private static SyncAction syncAction;
    private static EntityDao teamDao;
    private static AuditReader reader;

    @BeforeClass
    public static void getSyncManager() {
        syncAction = syncAction.getInstance();
        syncTrack = SyncTrack.getInstance();
        syncTrack.registerObserver(syncAction);
        reader = AuditReaderFactory.get(EntityManagerProvider.getInstance().getEntityManager());
        teamDao = new TeamDao();
    }

    @Test
    public void simulate() {

        final Team cowboys = getTeamById(2);
        // change the location of the Dallas Cowboys from Dallas to Austin
        cowboys.setLocation("Austin");

        final Team eagles = getTeamById(4);
        // change the location of the Philadelphia Cowboys from Philadelphia to New Jersey
        eagles.setLocation("New Jersey");

        // persist the changes
        saveTeam(cowboys);
        saveTeam(eagles);

        // change the location of the Austin Cowboys from Austin to San Antionio
        cowboys.setLocation("San Antonio");

        // persist the changes
        saveTeam(cowboys);

        // use Hibernate Envers to retreive the previous and current versions of the specific Team
        final Team currentTeamRevision = getCurrentRevision(cowboys.getId());
        final Team previousTeamRevision = getPreviousRevision(cowboys.getId());

        // determine what has changed between the previous and current versions of the cowboys Team entity
        diff(previousTeamRevision, currentTeamRevision)
                .forEach(e -> {
                    Assert.assertEquals("Changed attributes should be location", "location", e.getAttribute());
                    Assert.assertEquals("Previous value should be Austin", "Austin", e.getPreviousVal());
                    Assert.assertEquals("Updated value should be San Antonio", "San Antonio", e.getCurrentVal());
                    System.out.println(e);
                });

    }

    private Team getTeamById(final int id) {
        return (Team) teamDao.findOne(id);
    }

    private void saveTeam(final Team team) {
        teamDao.update(team);
    }

    private Team getCurrentRevision(final int id) {
        return reader.find(Team.class, id, getCurrentRevisionId(id));
    }

    private Team getPreviousRevision(final int id) {
        return reader.find(Team.class, id, getCurrentRevisionId(id) - 1);
    }

    private int getCurrentRevisionId(final int id) {
        return reader.getRevisions(Team.class, id).stream()
                .sorted((f1, f2) -> Long.compare(f2.intValue(), f1.intValue()))
                .findFirst().get().intValue();
    }

    private Set<ObjectDiff>  diff(final Object base, final Object compare) {
        final Set<ObjectDiff> changedObjects = new HashSet<ObjectDiff>();
        final DiffNode diff = ObjectDifferBuilder.buildDefault().compare(compare, base);
        diff.visit(new DiffNode.Visitor() {
            public void node(DiffNode node, Visit visit) {
                if(node.getParentNode() != null) {
                    changedObjects.add(
                            new ObjectDiff(
                                    node.getPropertyName(),
                                    node.canonicalGet(base).toString(),
                                    node.canonicalGet(compare).toString()
                            )
                    );
                }
            }
        });
        return changedObjects;
    }

    private class ObjectDiff {
        private final String attribute;
        private final String previousVal;
        private final String currentVal;

        public ObjectDiff(final String attribute, final String previousVal, final String currentVal) {
            this.attribute = attribute;
            this.previousVal = previousVal;
            this.currentVal = currentVal;
        }

        public String getAttribute() {
            return attribute;
        }

        public String getPreviousVal() {
            return previousVal;
        }

        public String getCurrentVal() {
            return currentVal;
        }

        @Override
        public String toString()
        {
            return ToStringBuilder.reflectionToString(this);
        }
    }

}