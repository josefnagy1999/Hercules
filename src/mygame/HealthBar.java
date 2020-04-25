package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
import jme3tools.optimize.TextureAtlas;

public class HealthBar {

    private Picture health, Face;
    private Texture2D healthTexture;
    private AssetManager assetManager;
    private float SettingsHeight, SettingsWidth;

    final private float FaceHeight, FaceWidth;
    final private float HealthHeight, HealthWidth;
    final private float CircleRatio = 170.0f / 247.0f;
    final private float LeftPart = 31.0f / 247.0f, Bottompart = 60.0f / 251.0f;
    
    final private float FaceRatio=247.0f/251.0f;
    
   final private float FaceX, FaceY , HealthX , healthY;
    
    public HealthBar(AssetManager assetManager, float Width, float Height) {

        this.assetManager = assetManager;

        this.SettingsHeight = Height;
        this.SettingsWidth = Width;

        FaceHeight = SettingsHeight / 6.0f;
        FaceWidth = FaceHeight *FaceRatio;

        HealthHeight = HealthWidth = CircleRatio * FaceWidth;
        
        FaceX=0  ;
        FaceY =SettingsHeight - FaceHeight ;
        
        HealthX=FaceX+LeftPart*FaceWidth;
        healthY=FaceY + Bottompart*FaceHeight;
        Initial();
    }

    void Initial() {

        healthTexture = (Texture2D) assetManager.loadTexture("Textures/Health.png");
        
        health = new Picture("Health Pic");
        health.setTexture(assetManager, healthTexture, true);
        health.setWidth(HealthWidth);
        
        health.setHeight(HealthHeight);
        health.setPosition(HealthX, healthY);
        
        Face = new Picture("Face Picture");
        Face.setImage(assetManager, "Textures/Face.png", true);

        Face.setWidth(FaceWidth);
        Face.setHeight(FaceHeight);
        Face.setPosition(FaceX, FaceY);
    }

    public Picture getHealth() {
        return health;
    }

    public Picture getFace() {
        return Face;
    }

}