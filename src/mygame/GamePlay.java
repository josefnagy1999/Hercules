/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import Player.Player;
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

    public GamePlay(rayCasting rycast, Player playerClass, Node Scene) {
        this.rycast = rycast;
        this.playerClass = playerClass;
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
                 N.detachAllChildren();
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
        if(BetterInputManager.Sword_Attack){
        detected = rycast.attack_detect();
        for (int i = 0; i < detected.size(); i++) {
            Geometry child = detected.get(i);
            String name = child.getName();
            String parent = "";
           
            if (name.equals("Box001_Material #41_0")) {
                //playerClass.TakeDamage(50);
                parent = "Bagmy";

            }
            Node N=find_parent(child, parent);
            if(N!=null)
            Scene.detachChild(N);
        }
        System.out.println(playerClass.getHealthCounter());
        }
        
        detected = rycast.touch_detect();
        for (int i = 0; i < detected.size(); i++) {
            Geometry child = detected.get(i);
            String name = child.getName();
            String parent = "";
            if (name.equals("12190_Heart_v1_L3-geom-0")) {
                playerClass.increaseHeartCounter();
                parent = "HeartShape";
            }
            if (name.equals("HealthDrink")) {
                playerClass.setHealthCounter(50);
                playerClass.TakeDamage(-50);
                parent = "HealthDrink";

            }
            if (name.equals("CoinObj_Coin_0")) {
                playerClass.increaseCoinCounter();
                parent = "Coin";

            }
            if (name.equals("Box001_Material #41_0")) {
                playerClass.TakeDamage(50);
                parent = "Bagmy";
                continue;

            }
            Node N=find_parent(child, parent);
            if(N!=null)
            Scene.detachChild(N);
        }
        
    }
    }
  


