# API Documentation

This document serves as a comprehensive guide to the Diamond Data API.

## AuthController
Below are all the endpoints for the Auth Controller:

```java
// /login - GET
public User login(@RequestParam String email, @RequestParam String password);

// /sign-up - POST
public User signUp(@RequestBody User user);

// /delete-acount - DELETE
public boolean deleteUser(@RequestParam Long id);
```

## DefensivePlayerController
```java
// /get - GET
public DefensivePlayer get(@RequestParam Long id);

// /get-all - GET
public List<DefensivePlayer> getAll();

// /get-by-team - GET
public List<DefensivePlayer> getByTeam(@RequestParam Long id);

// /create - POST
public DefensivePlayer create(@RequestBody DefensivePlayer player)

// /update - PUT
public DefensivePlayer update(@RequestParam Long id, @RequestBody DefensivePlayer newModel);

// /delete - DELETE
public boolean delete(@RequestParam Long id);
```

## OffensivePlayerController
```java
// /get - GET
public OffensivePlayer get(@RequestParam Long id);

// /get-all - GET
public List<OffensivePlayer> getAll();

// /get-by-team - GET
public List<OffensivePlayer> getByTeam(@RequestParam Long id);

// /create - POST
public OffensivePlayer create(@RequestBody OffensivePlayer player)

// /update - PUT
public OffensivePlayer update(@RequestParam Long id, @RequestBody OffensivePlayer newModel);

// /delete - DELETE
public boolean delete(@RequestParam Long id);
```

## PitcherController
```java
public Pitcher get(@RequestParam Long id);

// /get-all - GET
public List<Pitcher> getAll();

// /get-by-team - GET
public List<Pitcher> getByTeam(@RequestParam Long id);

// /create - POST
public Pitcher create(@RequestBody Pitcher player)

// /update - PUT
public Pitcher update(@RequestParam Long id, @RequestBody Pitcher newModel);

// /delete - DELETE
public boolean delete(@RequestParam Long id);
```

## RosterController
```java
// Empty at present.
```

## TeamController
```java
// /get - GET
public Team get(@RequestParam Long id);

// /get-all - GET
public List<Team> getAll();

// /create - POST
public Team create(@RequestBody Team team);

// /update - PUT
public Team update(@RequestParam Long id, @RequestBody Team newTeam);

// /delete - DELETE
public boolean delete(@RequestParam Long id);
```