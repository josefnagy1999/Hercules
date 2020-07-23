/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import Player.*;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.List;

/**
 *
 * @author Mariv & Youssef
 */
public class GamePlay {
    
    List<Geometry> detected;
    rayCasting rycast;
    Node Scene;
    private Player playerClass;
    private Node player;
    
    public GamePlay(Player playerClass, Node Scene) {
        this.playerClass = playerClass;
        this.player = this.playerClass.getPlayer();
        this.rycast = new rayCasting(player, Scene);
        this.Scene = Scene;
    }
    
    Node find_parent(Geometry geo, String parent) {
        try {
            Node N = geo.getParent();

            //if(N==null)System.out.println("i am null");
            Node par = null;
            if (Scene.getChild(parent) != null) {
                par = (Node) Scene.getChild(parent);
            }
            if (parent.equals("Scene Root")) {
                par = Scene;
            }
            
            while (N != null && !N.getName().equals(parent) && N.getParent() != Scene) {
                // N.detachAllChildren();
                N = N.getParent();
                
            }
            // System.out.println("hello");
            return N;
            
        } catch (Exception e) {
            System.out.println("mygame.rayCasting.find_parent()");
        }
        return null;
        
    }
    
    public void update() {
        if (BetterInputManager.Sword_Attack) {
            detected = rycast.detect_Collision_with("Sword");
            for (int i = 0; i < detected.size(); i++) {
                Geometry child = detected.get(i);
                String name = child.getName();
                String parent = "";
                
                if (name.equals("Box001_Material #41_0")) {
                    //playerClass.TakeDamage(50);
                    parent = "Bagmy";
                    
                }
                Node N = find_parent(child, parent);
                if (N != null) {
                    Scene.detachChild(N);
                }
            }
        }
        
        detected = rycast.detect_Collision_with("Hercules");
        for (int i = 0; i < detected.size(); i++) {
            Geometry child = detected.get(i);
            String name = child.getName();
            String parent = "";
            if (name.equals("12190_Heart_v1_L3-geom-0")) {
                playerClass.AddLife();
                parent = "HeartShape";
            }
            if (name.equals("HealthDrink")) {
                player.getControl(HealthBar.class).IncreaseHealth(50);
                parent = "HealthDrink";
                
            }
            if (name.equals("CoinObj_Coin_0")) {
                playerClass.increaseCoinCounter(3);
                parent = "Coin";
                
            }
            if (name.equals("Box001_Material #41_0")) {
                player.getControl(HealthBar.class).DecreaseHealth(50);
                parent = "Bagmy";
                continue;
                
            }
            Node N = find_parent(child, parent);
            if (N != null) {
                Scene.detachChild(N);
            }
        }
        
    }
}
