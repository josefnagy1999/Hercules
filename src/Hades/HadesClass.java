/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hades;

import GUI_2D.HealthBar;
import mygame.*;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;

/**
 *
 * @author isaac
 */
public class HadesClass {

    Node Hades;
    Node Hercules;
    Effects headFire , handFire;
    private HealthBar HadesHealth;
    private HadesMovement hadescontrol;

    public HadesClass(Node Hercules, AssetManager assetManager, BulletAppState bulletAppState, CameraNode camNode, Node GuiNode)
    {

        Hades = (Node) assetManager.loadModel("Models/Hades/Hades.j3o");
        Hades.setLocalTranslation(8, 1, 0);
        Hades.scale(2);
        this.Hercules = Hercules;

        HadesHealth = new HealthBar(camNode.getCamera(), GuiNode, 1000L, false);
        HadesHealth.SetHealthPic(assetManager, "Textures/Hades/Frame.png", "Textures/Hades/Blood.png");
        Hades.addControl(HadesHealth);

        hadescontrol = new HadesMovement(Hercules, Hades, bulletAppState);
        Hades.addControl(hadescontrol);
       

    }

    public Node getHades() {
        return Hades;
    }

}
