DELETE FROM sp24.dd_users  WHERE ghosted_date != 0;
DELETE FROM sp24.dd_teams WHERE ghosted_date != 0;
DELETE FROM sp24.dd_defense WHERE ghosted_date != 0;
DELETE FROM sp24.dd_offense WHERE ghosted_date != 0;
DELETE FROM sp24.dd_notes WHERE ghosted_date != 0;
DELETE FROM sp24.dd_players WHERE ghosted_date != 0;