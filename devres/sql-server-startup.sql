
CREATE TABLE [sp24].[dd_accounts] (
  [id] uniqueidentifier UNIQUE PRIMARY KEY NOT NULL,
  [userCount] INT NOT NULL DEFAULT (0)
)
GO

CREATE TABLE [sp24].[dd_users] (
  [id] uniqueidentifier UNIQUE PRIMARY KEY NOT NULL,
  [username] VARCHAR(MAX) NOT NULL,
  [passwordHash] VARCHAR(MAX) NOT NULL,
  [accountId] uniqueidentifier
)
GO

CREATE TABLE [sp24].[dd_teams] (
  [id] uniqueidentifier UNIQUE PRIMARY KEY NOT NULL,
  [accountId] uniqueidentifier NOT NULL,
  [name] VARCHAR(MAX)
)
GO

CREATE TABLE [sp24].[dd_members] (
  [id] uniqueidentifier UNIQUE PRIMARY KEY NOT NULL,
  [firstName] VARCHAR(MAX) NOT NULL,
  [lastName] VARCHAR(MAX) NOT NULL,
  [memberHash] VARCHAR(MAX),
  [position] VARCHAR(MAX) NOT NULL
)
GO


CREATE TABLE [sp24].[dd_defense] (
  [id] uniqueidentifier   PRIMARY KEY NOT NULL,
  [teamId] uniqueidentifier NOT NULL,
  [memberId] uniqueidentifier NOT NULL,
  [assists] INT DEFAULT (0),
  [caughtStealingPercentage] DECIMAL(15,2) DEFAULT (0),
  [doublePlays] INT DEFAULT (0),
  [errors] INT DEFAULT (0),
  [fieldingPercentage] DECIMAL(15,2) DEFAULT (0),
  [inningsPlayed] INT DEFAULT (0),
  [outs] INT DEFAULT (0),
  [outfieldAssists] INT DEFAULT (0),
  [passedBalls] INT DEFAULT (0),
  [putouts] INT DEFAULT (0),
  [totalChances] INT DEFAULT (0),
  [triplePlays] INT DEFAULT (0)
)
 

CREATE TABLE [sp24].[dd_offense_left] (
  [id] uniqueidentifier   PRIMARY KEY NOT NULL,
  [teamId] uniqueidentifier NOT NULL,
  [memberId] uniqueidentifier NOT NULL,
  [atBats] INT DEFAULT (0),
  [average] DECIMAL(15,2) DEFAULT (0),
  [caughtStealingPercentage] DECIMAL DEFAULT (0),
  [doubles] INT DEFAULT (0),
  [extraBaseHits] INT DEFAULT (0),
  [gamesPlayed] INT DEFAULT (0),
  [grandSlams] INT DEFAULT (0),
  [groundIntoDoublePlay] INT DEFAULT (0),
  [groundOutVsFlyOut] DECIMAL(15,2) DEFAULT (0),
  [hitByPitch] INT DEFAULT (0),
  [hits] INT DEFAULT (0),
  [homeruns] INT DEFAULT (0),
  [intentionalWalks] INT DEFAULT (0),
  [leftOnBase] INT DEFAULT (0),
  [onBasePercentage] DECIMAL(15,2) DEFAULT (0),
  [onBasePlusSlugging] DECIMAL(15,2) DEFAULT (0),
  [plateAppearances] INT DEFAULT (0),
  [reachedOnError] INT DEFAULT (0),
  [runs] INT DEFAULT (0),
  [runsBattedIn] INT DEFAULT (0),
  [scarificeBunts] INT DEFAULT (0),
  [sacrificeFlies] INT DEFAULT (0),
  [singles] INT DEFAULT (0),
  [slugging] DECIMAL DEFAULT (0),
  [stolenBases] INT DEFAULT (0),
  [totalBases] INT DEFAULT (0),
  [triples] INT DEFAULT (0),
  [walks] INT DEFAULT (0),
  [walkOffs] INT DEFAULT (0)
)
 

CREATE TABLE [sp24].[dd_offense_right] (
  [id] uniqueidentifier   PRIMARY KEY NOT NULL,
  [teamId] uniqueidentifier NOT NULL,
  [memberId] uniqueidentifier NOT NULL,
  [atBats] INT DEFAULT (0),
  [average] DECIMAL(15,2) DEFAULT (0),
  [caughtStealingPercentage] DECIMAL DEFAULT (0),
  [doubles] INT DEFAULT (0),
  [extraBaseHits] INT DEFAULT (0),
  [gamesPlayed] INT DEFAULT (0),
  [grandSlams] INT DEFAULT (0),
  [groundIntoDoublePlay] INT DEFAULT (0),
  [groundOutVsFlyOut] DECIMAL(15,2) DEFAULT (0),
  [hitByPitch] INT DEFAULT (0),
  [hits] INT DEFAULT (0),
  [homeruns] INT DEFAULT (0),
  [intentionalWalks] INT DEFAULT (0),
  [leftOnBase] INT DEFAULT (0),
  [onBasePercentage] DECIMAL(15,2) DEFAULT (0),
  [onBasePlusSlugging] DECIMAL(15,2) DEFAULT (0),
  [plateAppearances] INT DEFAULT (0),
  [reachedOnError] INT DEFAULT (0),
  [runs] INT DEFAULT (0),
  [runsBattedIn] INT DEFAULT (0),
  [scarificeBunts] INT DEFAULT (0),
  [sacrificeFlies] INT DEFAULT (0),
  [singles] INT DEFAULT (0),
  [slugging] DECIMAL DEFAULT (0),
  [stolenBases] INT DEFAULT (0),
  [totalBases] INT DEFAULT (0),
  [triples] INT DEFAULT (0),
  [walks] INT DEFAULT (0),
  [walkOffs] INT DEFAULT (0)
)
 

CREATE TABLE [sp24].[dd_rhp] (
  [id] uniqueidentifier   PRIMARY KEY NOT NULL,
  [teamId] uniqueidentifier NOT NULL,
  [memberId] uniqueidentifier NOT NULL,
  [appearances] INT DEFAULT (0),
  [balks] INT DEFAULT (0),
  [battersFaced] INT DEFAULT (0),
  [blownSaves] INT DEFAULT (0),
  [completeGames] INT DEFAULT (0),
  [earnedRunsAllowed] INT DEFAULT (0),
  [earnedRunAverage] DECIMAL(15,2) DEFAULT (0),
  [flyouts] INT DEFAULT (0),
  [gamesFinished] INT DEFAULT (0),
  [gamesStarted] INT DEFAULT (0),
  [groundouts] INT DEFAULT (0),
  [holds] INT DEFAULT (0),
  [inheritedRunners] INT DEFAULT (0),
  [iningsPitched] DECIMAL DEFAULT (0),
  [losses] INT DEFAULT (0),
  [numberOfPitches] INT DEFAULT (0),
  [pickoffs] INT DEFAULT (0),
  [qualityStarts] INT DEFAULT (0),
  [reliefWins] INT DEFAULT (0),
  [saves] INT DEFAULT (0),
  [saveOpportunities] INT DEFAULT (0),
  [savePercentage] DECIMAL(15,2) DEFAULT (0),
  [shutouts] INT DEFAULT (0),
  [strikeouts] INT DEFAULT (0),
  [unearnedRuns] INT DEFAULT (0),
  [walksAndHitsPerInningsPitched] DECIMAL DEFAULT (0),
  [wildPitches] INT DEFAULT (0),
  [wins] INT DEFAULT (0),
  [winningPercentage] DECIMAL(15,2) DEFAULT (0)
)
 

CREATE TABLE [sp24].[dd_lhp] (
  [id] uniqueidentifier   PRIMARY KEY NOT NULL,
  [teamId] uniqueidentifier NOT NULL,
  [memberId] uniqueidentifier NOT NULL,
  [appearances] INT DEFAULT (0),
  [balks] INT DEFAULT (0),
  [battersFaced] INT DEFAULT (0),
  [blownSaves] INT DEFAULT (0),
  [completeGames] INT DEFAULT (0),
  [earnedRunsAllowed] INT DEFAULT (0),
  [earnedRunAverage] DECIMAL(15,2) DEFAULT (0),
  [flyouts] INT DEFAULT (0),
  [gamesFinished] INT DEFAULT (0),
  [gamesStarted] INT DEFAULT (0),
  [groundouts] INT DEFAULT (0),
  [holds] INT DEFAULT (0),
  [inheritedRunners] INT DEFAULT (0),
  [iningsPitched] DECIMAL DEFAULT (0),
  [losses] INT DEFAULT (0),
  [numberOfPitches] INT DEFAULT (0),
  [pickoffs] INT DEFAULT (0),
  [qualityStarts] INT DEFAULT (0),
  [reliefWins] INT DEFAULT (0),
  [saves] INT DEFAULT (0),
  [saveOpportunities] INT DEFAULT (0),
  [savePercentage] DECIMAL(15,2) DEFAULT (0),
  [shutouts] INT DEFAULT (0),
  [strikeouts] INT DEFAULT (0),
  [unearnedRuns] INT DEFAULT (0),
  [walksAndHitsPerInningsPitched] DECIMAL DEFAULT (0),
  [wildPitches] INT DEFAULT (0),
  [wins] INT DEFAULT (0),
  [winningPercentage] DECIMAL(15,2) DEFAULT (0)
)
 

CREATE TABLE [sp24].[dd_rosters] (
  [id] uniqueidentifier   PRIMARY KEY NOT NULL,
  [managerId] uniqueidentifier NOT NULL,
  [expiry] DATE NOT NULL DEFAULT '7 days from current date',
  [catcher] uniqueidentifier NOT NULL,
  [firstBase] uniqueidentifier NOT NULL,
  [secondBase] uniqueidentifier NOT NULL,
  [thirdBase] uniqueidentifier NOT NULL,
  [shortstop] uniqueidentifier NOT NULL,
  [leftField] uniqueidentifier NOT NULL,
  [rightField] uniqueidentifier NOT NULL,
  [centerField] uniqueidentifier NOT NULL,
  [startingPitcher] uniqueidentifier NOT NULL
)
 

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'Used to prevent one player from being entered multiple times',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'dd_members',
@level2type = N'Column', @level2name = 'memberHash';
 

ALTER TABLE [dd_users] ADD FOREIGN KEY ([accountId]) REFERENCES [dd_accounts] ([id])
 

ALTER TABLE [dd_teams] ADD FOREIGN KEY ([accountId]) REFERENCES [dd_accounts] ([id])
 

ALTER TABLE [dd_defense] ADD FOREIGN KEY ([teamId]) REFERENCES [dd_teams] ([id])
 

ALTER TABLE [dd_defense] ADD FOREIGN KEY ([memberId]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_offense_left] ADD FOREIGN KEY ([teamId]) REFERENCES [dd_teams] ([id])
 

ALTER TABLE [dd_offense_left] ADD FOREIGN KEY ([memberId]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_offense_right] ADD FOREIGN KEY ([teamId]) REFERENCES [dd_teams] ([id])
 

ALTER TABLE [dd_offense_right] ADD FOREIGN KEY ([memberId]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_lhp] ADD FOREIGN KEY ([memberId]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rhp] ADD FOREIGN KEY ([memberId]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_lhp] ADD FOREIGN KEY ([teamId]) REFERENCES [dd_teams] ([id])
 

ALTER TABLE [dd_rhp] ADD FOREIGN KEY ([teamId]) REFERENCES [dd_teams] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([managerId]) REFERENCES [dd_users] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([startingPitcher]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([catcher]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([firstBase]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([secondBase]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([thirdBase]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([shortstop]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([leftField]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([centerField]) REFERENCES [dd_members] ([id])
 

ALTER TABLE [dd_rosters] ADD FOREIGN KEY ([rightField]) REFERENCES [dd_members] ([id])
 
