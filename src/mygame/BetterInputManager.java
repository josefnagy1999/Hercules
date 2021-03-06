/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author isaac
 */
public class BetterInputManager {

    private InputManager inputManager;
    public static boolean Forward = false, BackWard = false, Left = false, 
            Right = false, Jump =false , Run=false,Punch=false,Power_Punch=false,Sword_Attack=false, Pause=false;
    public static float MouseX = 0, MouseY = 0;
    public static int dir=0;
    public BetterInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
        intialKeys();
    }

    private void intialKeys() {
        // Mouse 
        inputManager.addMapping("Mouse Right", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("Mouse Left", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("Mouse Up", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("Mouse Down", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping("Sword Attack", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(analogListener, "Mouse Right", "Mouse Left", "Mouse Up", "Mouse Down");

        //KeyBoards
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Run", new KeyTrigger(KeyInput.KEY_LSHIFT));  
        inputManager.addMapping("Back", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Punch", new KeyTrigger(KeyInput.KEY_LCONTROL));
        inputManager.addMapping("Power Punch", new KeyTrigger(KeyInput.KEY_LMENU));
        inputManager.deleteMapping(SimpleApplication.INPUT_MAPPING_EXIT);
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_ESCAPE), new KeyTrigger(KeyInput.KEY_P), new KeyTrigger(KeyInput.KEY_PAUSE));
       
        inputManager.addListener(actionListener, "Left", "Right");
        inputManager.addListener(actionListener, "Forward", "Back", "Jump" , "Run");
        inputManager.addListener(actionListener, "Punch","Power Punch","Sword Attack");
        inputManager.addListener(actionListener, "Pause");
    }

    private final ActionListener actionListener = new ActionListener() {

        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {

            switch (name) {
                
                case "Forward":
                    Forward = keyPressed;
                    break;
                case "Back":
                    BackWard = keyPressed;
                    break;
                case "Right":
                    Right = keyPressed;
                    break;
                case "Left":
                    Left = keyPressed;
                    break;
                case "Jump":
                    Jump = keyPressed;
                    break;
                case "Run":
                    Run=keyPressed;
                    break;
                case "Punch":
                    Punch=keyPressed;
                    break;
                case "Power Punch":
                    Power_Punch=keyPressed;
                    break;
                 case "Sword Attack":
                    Sword_Attack=keyPressed;
                        break; 
                case "Pause":
                    Pause=keyPressed;
                default:
                    break;
            }
        }
    };

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {

            if (null != name) {
                
                switch (name) {
                    case "Mouse Right":
                        MouseX = value;
                        break;
                    case "Mouse Left":
                        MouseX = -value;
                        break;
                    case "Mouse Up":
                        MouseY = value ;
                        dir=1;
                        break;
                    case "Mouse Down":
                        MouseY = value;
                        dir=-1;
                        break;

                    default:                    
                        break;
                }
            }
        }
    };
}
