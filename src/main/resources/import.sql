insert into TEAM(ID,NAME, LOCATION)  values(1, '49ers', 'San Francisco');
insert into TEAM(ID,NAME, LOCATION)  values(2, 'Cowboys', 'Dallas');
insert into TEAM(ID,NAME, LOCATION)  values(3, 'Steelers', 'Pittsburgh');
insert into TEAM(ID,NAME, LOCATION)  values(4, 'Eagles', 'Philadelphia');

insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(1, 1, 'Jerry', 'Rice', 'Wide Receiver', 99);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(2, 1, 'Joe', 'Montana', 'Quaterback', 99);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(3, 1, 'Ronnie', 'Lott', 'Safety', 92);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(4, 2, 'Troy', 'Aikman', 'Quaterback', 87);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(5, 2, 'Emmitt', 'Smith', 'Running Back', 95);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(6, 2, 'Dieon', 'Sanders', 'Cornerback', 99);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(7, 3, 'Antonio', 'Brown', 'Wide Receiver', 97);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(8, 3, 'Franco', 'Harris', 'Running Back', 75);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(9, 3, 'James', 'Harrison', 'Linebacker', 94);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(10, 4, 'Carson', 'Wentz', 'Quaterback',86);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(11, 4, 'Reggie', 'White', 'Defensive End', 98);
insert into PLAYER(ID,TEAM_ID, FIRSTNAME, SURNAME, POSITION, RATING)  values(12, 4, 'Chris', 'Carter', 'Wide Receiver', 95);

insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(1, 1, 'RED', '#00FFEE');
insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(2, 1, 'GOLD', '#55LLGG');
insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(3, 2, 'BLUE', '#GDGDFGD');
insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(4, 2, 'WHITE', '#872UHFF');
insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(5, 3, 'YELLOW', '#00FFEE');
insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(6, 3, 'BLACK', '#55LLGG');
insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(7, 4, 'GREEN', '#746Y2R');
insert into TEAM_COLOUR(ID,TEAM_ID, NAME, HTMLCODE)  values(8, 4, 'WHITE', '#ZXAE216');

insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(1, 1, 520000, 375000, 1256, 5438);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(2, 2, 8765432, 1654367, 75463, 31431);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(3, 3, 523552, 352565, 313134, 97567);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(4, 4, 6464334, 244234, 453425, 12133);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(5, 5, 52521552, 34869657, 84752, 765346);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(6, 6, 9876553, 956732235, 1236565, 2342435);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(7, 7, 1234554, 4346234, 124343, 255235);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(8, 8, 796574, 123987656, 766454, 545235);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(9, 9, 53552, 7575, 454364, 543345);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(10, 10, 876876, 123987656, 543257, 66346);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(11, 11, 4244, 43324, 3424564, 7673565);
insert into SALARY(ID,PLAYER_ID, GROSS, NET, MEDICAL_CONTRIBUTION, CHARITABLE_CONTRIBUTION)  values(12, 12, 9789, 75767, 75463, 4234);