package com.dd.api.restapi.factories;

import com.dd.api.restapi.builders.DefensivePlayerBuilder;
import com.dd.api.restapi.models.DefensivePlayer;
import com.sun.source.tree.BreakTree;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class DefensivePlayerFactory {
    public static DefensivePlayer getPlayerById(UUID userId, Connection connection) {
    
        DefensivePlayer player = null;
        
        String sql = "USE web3_530; SELECT * from sp24.dd_defense WHERE id = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId.toString());
            ResultSet set = statement.executeQuery();
            
            while(set.next()) {
                DefensivePlayerBuilder builder = new DefensivePlayerBuilder();
                return builder.setId(userId)
                    .setTeamId(UUID.fromString(set.getString("teamId")))
                    .setMemberId(UUID.fromString(set.getString("memberId")))
                    .setAssists(set.getInt("assists"))
                    .setCaughtStealingPercentage(set.getDouble("caughtStealingPercentage"))
                    .setDoublePlays(set.getInt("doublePlays"))
                    .setErrors(set.getInt("errors"))
                    .setInningsPlayed(set.getInt("inningsPlayed"))
                    .setOuts(set.getInt("outs"))
                    .setOutfieldAssists(set.getInt("outfieldAssists"))
                    .setPassedBalls(set.getInt("passedBalls"))
                    .setPutouts(set.getInt("putouts"))
                    .setTotalChances(set.getInt("totalChances"))
                    .setTriplePlays(set.getInt("triplePlays"))
                    .createDefensivePlayer();
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
        return null;
    }
    
    
    public static DefensivePlayer createPlayer(DefensivePlayer player, Connection connection) {
    
         String sql = """
             INSERT INTO sp24.dd_defense  (
               [id],
               [teamId],
               [memberId],
               [assists],
               [caughtStealingPercentage],
               [doublePlays],
               [errors],
               [fieldingPercentage],
               [inningsPlayed],
               [outs],
               [outfieldAssists],
               [passedBalls],
               [putouts],
               [totalChances],
               [triplePlays]
             ) VALUES ( ?, ?, ?,
                        ?, ?, ?,
                        ?, ?, ?,
                        ?, ?, ?,
                        ?, ?, ? );
         """;
         
         try(PreparedStatement statement = connection.prepareStatement(sql)) {
             statement.setString(1, player.getId().toString());
             statement.setString(2, player.getTeamId().toString());
             statement.setString(3, player.getMemberId().toString());
         }
         catch (Exception ex) {
         
         }
    
        return  null;
        
    }
}
