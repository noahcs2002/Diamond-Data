DELETE FROM dd_users  WHERE ghosted_date != 0;
DELETE FROM dd_teams WHERE ghosted_date != 0;
DELETE FROM dd_defense WHERE ghosted_date != 0;
DELETE FROM dd_offense WHERE ghosted_date != 0;
DELETE FROM dd_notes WHERE ghosted_date != 0;
DELETE FROM dd_players WHERE ghosted_date != 0;