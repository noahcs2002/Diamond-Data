CREATE TABLE [Accounts] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [userCount] INT NOT NULL DEFAULT (0)
)
GO

CREATE TABLE [Users] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [username] VARCHAR(MAX) NOT NULL,
  [passwordHash] VARCHAR(MAX) NOT NULL,
  [accountId] UUID
)
GO

CREATE TABLE [Teams] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [accountId] UUID NOT NULL,
  [name] VARCHAR(MAX)
)
GO

CREATE TABLE [Members] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [firstName] VARCHAR(MAX) NOT NULL,
  [lastName] VARCHAR(MAX) NOT NULL,
  [memberHash] VARCHAR(MAX),
  [position] VARCHAR(MAX) NOT NULL
)
GO

CREATE TABLE [Defense] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [teamId] UUID NOT NULL,
  [memberId] UUID NOT NULL,
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
GO

CREATE TABLE [OffenseLeft] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [teamId] UUID NOT NULL,
  [memberId] UUID NOT NULL,
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
GO

CREATE TABLE [OffenseRight] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [teamId] UUID NOT NULL,
  [memberId] UUID NOT NULL,
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
GO

CREATE TABLE [RHP] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [teamId] UUID NOT NULL,
  [memberId] UUID NOT NULL,
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
GO

CREATE TABLE [LHP] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [teamId] UUID NOT NULL,
  [memberId] UUID NOT NULL,
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
GO

CREATE TABLE [Rosters] (
  [id] UUID UNIQUE PRIMARY KEY NOT NULL,
  [managerId] UUID NOT NULL,
  [expiry] DATE NOT NULL DEFAULT '7 days from current date',
  [catcher] UUID NOT NULL,
  [firstBase] UUID NOT NULL,
  [secondBase] UUID NOT NULL,
  [thirdBase] UUID NOT NULL,
  [shortstop] UUID NOT NULL,
  [leftField] UUID NOT NULL,
  [rightField] UUID NOT NULL,
  [centerField] UUID NOT NULL,
  [startingPitcher] UUID NOT NULL
)
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'Used to prevent one player from being entered multiple times',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Members',
@level2type = N'Column', @level2name = 'memberHash';
GO

ALTER TABLE [Users] ADD FOREIGN KEY ([accountId]) REFERENCES [Accounts] ([id])
GO

ALTER TABLE [Teams] ADD FOREIGN KEY ([accountId]) REFERENCES [Accounts] ([id])
GO

ALTER TABLE [Defense] ADD FOREIGN KEY ([teamId]) REFERENCES [Teams] ([id])
GO

ALTER TABLE [Defense] ADD FOREIGN KEY ([memberId]) REFERENCES [Members] ([id])
GO

ALTER TABLE [OffenseLeft] ADD FOREIGN KEY ([teamId]) REFERENCES [Teams] ([id])
GO

ALTER TABLE [OffenseLeft] ADD FOREIGN KEY ([memberId]) REFERENCES [Members] ([id])
GO

ALTER TABLE [OffenseRight] ADD FOREIGN KEY ([teamId]) REFERENCES [Teams] ([id])
GO

ALTER TABLE [OffenseRight] ADD FOREIGN KEY ([memberId]) REFERENCES [Members] ([id])
GO

ALTER TABLE [LHP] ADD FOREIGN KEY ([memberId]) REFERENCES [Members] ([id])
GO

ALTER TABLE [RHP] ADD FOREIGN KEY ([memberId]) REFERENCES [Members] ([id])
GO

ALTER TABLE [LHP] ADD FOREIGN KEY ([teamId]) REFERENCES [Teams] ([id])
GO

ALTER TABLE [RHP] ADD FOREIGN KEY ([teamId]) REFERENCES [Teams] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([managerId]) REFERENCES [Users] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([startingPitcher]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([catcher]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([firstBase]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([secondBase]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([thirdBase]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([shortstop]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([leftField]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([centerField]) REFERENCES [Members] ([id])
GO

ALTER TABLE [Rosters] ADD FOREIGN KEY ([rightField]) REFERENCES [Members] ([id])
GO
