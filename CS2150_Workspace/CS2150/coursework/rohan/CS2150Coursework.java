/* CS12150CourseWork.java
 * A simple scene consisting of a lit house and a textured ground plane
 * 27/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(20,1,20) T(0,-1,-10)] Ground plane
 *  |
 *  +-- [S(20,1,10) Rx(90) T(0,4,-20)] Sky plane
 *  |
 *  +-- [T(4,MoonY,-19)] Moon
 *  |
 *  +-- [T(-1.9,-1,-12)] Tree 1
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 *  |
 *  +-- [T(-1.0,-1,-12)] Tree 2
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 *  |
 *  +-- [T(0,-1,-12)] Tree 3
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 *  |
 *  +-- [T(2.0,-1,-12)] Tree 4
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 *  |
 *  +-- [Ry(189) S(0.5,0.5,0.5) T(8.0,0.2,-10)] Boat base
 *  |    |
 *  |    +-- [S(0.5,0.5,0.5) T(8.0,0.2,-10)] Roof of the boat
 *  |
 *  +-- [Ry(180) S(0.1,0.1,0.1) T(-0.5,-0.3,-1.2)] Car base    
 *  |    |
 *  |    +-- [S(0.1,0.1,0.1) T(-0.5,-0.24,-1.2)] Roof of the car
 *  |    |
 *  |    +-- [S(0.1,0.1,0.1) T(-0.47,-0.3,-1.10)] wheel 1 of the car
 *  |    |
 *  |    +-- [S(0.1,0.1,0.1) T(-0.83,-0.3,-1.10)] wheel 2 of the car
 *  |
 *  +-- [Ry(180) S(0.13,0.13,0.13) T(0.5,-0.3,-1.6)] helicopter base
 *  |    |
 *  |    +-- [Rx(-35) S(0.08,0.08,0.08) T(0.38,-0.13,-1.19)] Wings of the helicopter
 *  |    |
 *  |    +-- [ S(0.08,0.08,0.08) T(0.38,-0.16,-1.2)] COT of the helicopter
 *  |    
 *  |    
 *       
 */
package coursework.rohan;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of user input and various types of animation
 * to add a dynamic aspect to a 3D scene
 * 
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * <li>Press U to raise the helicoptor raise up to maximum level and press the next key      
 * <li>Press right key to make all vehicles move
 * <li>Press left key to make all vehicles come back to the starting positions
 * 
 * </ul>
 *
 * 
 *
 * @author Rohan singamsetty
 */
public class  CS2150Coursework  extends GraphicsLab
{
    /** display list id for the boat base */
    private final int boatBaseList = 1;
    /** display list id for the plane */
    private final int planeList = 3;
    /** display list id for the boat */
    private final int boatCubeList = 5;
    /** display list id for the carBody  */
    private final int carList = 6;
    /** display list id for the carRoof  */
    private final int carRoofList = 7;
    /** display list id for the carRoof  */
    private final int carWheelList = 8;
    /** display list id for the wings  */
    private final int wingsList = 9;
    /** display list id for the connector on top  */
    private final int cotList = 10;
    /** display list id for the body of helicopter  */
    private final int heliBodyList = 11;

    
    
   
    


    
    
    /** the moon's current Y offset from the scene origin */
    private float currentSunMoonY = 7.0f;
    /** the moon's highest possible Y offset */
    private final float highestSunMoonY = currentSunMoonY;
    /** the moon's lowest possible Y offset */
    private final float lowestSunMoonY  = 2.0f;
    /** is the moon rising? (false = the moon is falling) */
    private boolean risingSunMoon = true;
    
   
    
    /** the moon's current X offset from the scene origin */
    private float currentSphereX = 9.0f;
    /** the moon's highest possible x offset */
    private  final float highestSphereX  = currentSphereX;
    /** the moon's lowest possible x offset */
    private final float lowestSphereX = -11.0f;
    /** is the moon going left (false = the moon is going right ) */
    private boolean isStill = true;
    
    
    
    /** the boat base current X offset from the scene origin */
    private float currentHouseX = 8.0f;
    /** the boat base highest possible x offset */
    private  final float highestHouseX  = currentHouseX;
    /** the boat base lowest possible x offset */
    private final float lowestHouseX = -8.0f;
    /** is the boat base going right (false = the base is going right ) */
    private boolean stillHouse = true;
    
    
    
    /** the boat box current X offset from the scene origin */
    private float currentBoatBoxX = 8.0f;
    /** the boat box highest possible x offset */
    private  final float highestBoatBoxX  = currentHouseX;
    /** the boat box lowest possible x offset */
    private final float lowestBoatBoxX = -8.0f;
    /** is the boat box going right (false = the box is going right ) */
    private boolean stillBoat = true;
    
    
    
    /** the car base current X offset from the scene origin */
    private float currentCarBaseX = -0.5f;
    /** the car base highest possible x offset */
    private  final float highestCarBaseX  = currentCarBaseX;
    /** the car base lowest possible x offset */
    private final float lowestCarBaseX = 4.0f;
    /** is the car base going right (false = the car base is going right ) */
    private boolean stillCarBase = true;
    
    
    
    /** the car roof current X offset from the scene origin */
    private float currentCarRoofX = -0.5f;
    /** the car roof highest possible x offset */
    private  final float highestCarRoofX  = currentCarRoofX;
    /** the car roof lowest possible x offset */
    private final float lowestCarRoofX = 3.0f;
    /** is the roof going right (false = the roof is going right ) */
    private boolean stillCarRoof = true;
    
    
    
    /** the wheel 1 current X offset from the scene origin */
    private float currentCarWheel1X = -0.47f;
    /** the wheel 1 highest possible x offset */
    private  final float highestCarWheelX  = currentCarWheel1X;
    /** the wheel 1 lowest possible x offset */
    private final float lowestCarWheel1X = 3.0f;
    /** is the wheel 1 going right (false = the wheel 1 is going right ) */
    private boolean stillCarWheel1 = true;
    
    
    
    /** the wheel 2 current X offset from the scene origin */
    private float currentCarWheel2X = -0.83f;
    /** the wheel 2 highest possible x offset */
    private  final float highestCarWhee2X  = currentCarWheel2X;
    /** the wheel 2 lowest possible x offset */
    private final float lowestCarWheel2X = 3.0f;
    /** is the wheel 2 going right (false = the wheel 2 is going right ) */
    boolean stillCarWheel2 = true;
    
    
    
    /** the wings current Y offset from the scene origin */
    private float currentWingY = -0.13f;
    /** the wings highest possible y offset */
    private  final float highestWingY  = currentWingY;
    /** the wings lowest possible y offset */
    private final float lowestWingY = 0.4f;
    /** is the wings base going up (false = the wings is going down ) */
    private boolean  isStillWingUp = true;
    
    
    
    /** the wings current X offset from the scene origin */
    private float currentWingX = 0.38f;
    /** the wings highest possible x offset */
    private  final float highestWingX  = currentWingX;
    /** the wings lowest possible x offset */
    private final float lowestWingX = -1.38f;
    /** is the wings going right (false = the wings is going right ) */
    private boolean  isStillWingSide = true;
    
    
    
    /** the helicopter base current Y offset from the scene origin */
    private float currentHeliBaseY = -0.3f;
    /** the helicopter highest possible y offset */
    private  final float highestHeliBaseY  = currentHeliBaseY;
    /** the helicopter lowest possible y offset */
    private final float lowestHeliBaseY = 0.371f;
    /** is the helicopter base going up (false = the helicopter base is going down ) */
    private boolean  isStillHeliBase = true;
    
    
    
    
    /** the helicopter base current X offset from the scene origin */
    private float currentHeliBaseX = 0.5f;
    /** the helicopter highest possible x offset */
    private  final float highestHeliBaseX  = currentHeliBaseX;
    /** the helicopter lowest possible x offset */
    private final float lowestHeliBaseX = -1.5f;
    /** is the helicopter base going right (false = the helicopter base is going right ) */
    private boolean  isStillHeliBaseSide = true;
    
    
    private float wingsRotationAngle= -35.0f;
    
    private boolean  isStillWing = true;

   
    
    
    
    /** ids for nearest, linear and mipmapped textures for the ground plane */
    private Texture groundTextures;
    /** ids for nearest, linear and mipmapped textures for the daytime back (sky) plane */
    //private Texture skyDayTextures;
    /** ids for nearest, linear and mipmapped textures for the night time back (sky) plane */
    private Texture skyNightTextures;

    public static void main(String args[])
    {   new CS2150Coursework().run(WINDOWED,"CS2150 Coursework Submission",0.001f);
    }

    protected void initScene() throws Exception
    {
        // load the textures
        groundTextures = loadTexture("Lab6/textures/MyGrass.bmp");
        //skyDayTextures = loadTexture("Lab6/textures/daySky.bmp");
        skyNightTextures = loadTexture("Lab6/textures/starSky.bmp");

        // global ambient light level
        float globalAmbient[]   = {0.8f,  0.8f,  0.8f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient));

        // the first light for the scene is soft blue...
        float diffuse0[]  = { 0.2f,  0.2f, 0.4f, 1.0f};
        // ...with a very dim ambient contribution...
        float ambient0[]  = { 0.05f,  0.05f, 0.05f, 1.0f};
        // ...and is positioned above the viewpoint
        float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f};

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);
      
        
        // prepare the display lists for later use
        GL11.glNewList(boatBaseList,GL11.GL_COMPILE);
        {  	        drawUnitCube();

        }
        GL11.glEndList();
        GL11.glEndList();
        GL11.glNewList(planeList,GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
        GL11.glNewList(boatCubeList, GL11.GL_COMPILE);
        {   drawUnitCube2();
        }
        GL11.glEndList();
        GL11.glNewList(carList, GL11.GL_COMPILE);
        {   drawUnitBaseCar();
        }
        GL11.glEndList();
        GL11.glNewList(carRoofList, GL11.GL_COMPILE);
        {   drawUnitRoofCar();
        }
        GL11.glEndList();
        GL11.glNewList(carWheelList, GL11.GL_COMPILE);
        {   drawUnitWheel();
        }
        GL11.glEndList();
        GL11.glNewList(wingsList, GL11.GL_COMPILE);
        {   drawUnitWing();
        }
        GL11.glEndList();
        GL11.glNewList(cotList, GL11.GL_COMPILE);
        {   drawUnitConectorTop();
        }
        GL11.glEndList();
        GL11.glNewList(heliBodyList, GL11.GL_COMPILE);
        {   drawUnitBodyHelicopter();
        }
        GL11.glEndList();
       
        
        
        
        }
    
    protected void checkSceneInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_U))
        {   risingSunMoon = true;
       
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {   risingSunMoon = false;
        
        }
        
        
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {   
        isStillWingUp =true;
        isStillHeliBase = true;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_U))
        {   
        isStillWingUp = false;
        isStillHeliBase = false;
        }
        
         if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {   
        	isStill = true;
            stillHouse = true;
            stillBoat = true;
            stillCarBase = true;
            stillCarRoof = true;
            stillCarWheel1 = true;
            stillCarWheel2 = true;
            isStillWing = true;
            isStillWingSide = true;
            isStillHeliBaseSide = true;
            
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {   
        	isStill = false;
            stillHouse = false;
            stillBoat = false;
            stillCarBase = false;
            stillCarRoof = false;
            stillCarWheel1 = false;
            stillCarWheel2 = false;
            isStillWing = false;
            isStillWingSide = false;
            isStillHeliBaseSide = false;
            
         }else  if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {   resetAnimations();
        }
         
             isStillWing = true;
            for(int i =0 ;i <1000 ;i++) {
     	    wingsRotationAngle += 1.0f * getAnimationScale();
            }
        
         
         
         
        
        
         
         
        
    }
    protected void updateScene()
    {
        // if the sun/moon is rising, and it isn't at its highest
        // then increment the sun/moon's Y offset
        if(risingSunMoon && currentSunMoonY < highestSunMoonY)
        {  
        	currentSunMoonY += 0.001f * getAnimationScale();
        }
        // else if the sun/moon is falling, and it isn't at its lowest,
        // then decrement the sun/moon's Y offset
        else if(!risingSunMoon && currentSunMoonY > lowestSunMoonY)
        {  
        	currentSunMoonY -= 0.001f * getAnimationScale();
        }
        
        
        
        // if the sphere is going left, and it isn't at its highest
        // then increment the sphere Y offset
        if(isStill & currentSphereX < highestSphereX ) {
        	currentSphereX += 1.0f * getAnimationScale();
        }
         // else if the sphere is going right, and it isn't at its lowest,
        // then decrement the sphere X offset
        else if(!isStill & currentSphereX > lowestSphereX ) {
        	currentSphereX -= 1.0f * getAnimationScale();
        }
        
       
        
        // if the boat base is going left, and it isn't at its highest
        // then increment the base X offset
        if(stillHouse & currentHouseX < highestHouseX ) 
          {
        	currentHouseX += 1.0f * getAnimationScale();
          }
        // else if the boat base is going right, and it isn't at its lowest,
        // then decrement the base X offset
        else if(!stillHouse& currentHouseX > lowestHouseX ) 
         {
        	currentHouseX -= 1.0f * getAnimationScale();
         }
        
        
       
        // if the boat is going left, and it isn't at its highest
        // then increment the boat's X offset
        if(stillBoat & currentBoatBoxX < highestBoatBoxX ) 
           {
        	currentBoatBoxX += 1.0f * getAnimationScale();
           }
     // else if the boat is going left, and it isn't at its lowest,
        // then decrement the boat X offset
       else if(!stillBoat & currentBoatBoxX > lowestBoatBoxX ) 
           {
        	currentBoatBoxX -= 1.0f * getAnimationScale();
           }
        
        
        
        // if the car base is going Right, and it isn't at its highest
        // then increment the car base X offset
        if(stillCarBase & currentCarBaseX > highestCarBaseX ) 
           {
        	currentCarBaseX -= 0.8f * getAnimationScale();
           }
     // else if the car base is going left, and it isn't at its lowest,
        // then decrement the base X offset
       else if(!stillCarBase & currentCarBaseX < lowestCarBaseX ) 
           {
        	currentCarBaseX += 0.8f * getAnimationScale();
           }
        
        
        
        // if the car roof is going Right, and it isn't at its highest
        // then increment the house X offset
        if(stillCarRoof & currentCarRoofX > highestCarRoofX ) 
            {
        	currentCarRoofX -= 0.8f * getAnimationScale();
            }
     // else if the roof is going left, and it isn't at its lowest,
        // then decrement the roofs X offset
       else if(!stillCarRoof & currentCarRoofX < lowestCarRoofX ) 
            {
        	currentCarRoofX += 0.8f * getAnimationScale();
            }
        
        
        
        // if the car wheels is going right, and it isn't at its highest
        // then increment the car wheels X offset
        if(stillCarWheel1 & currentCarWheel1X > highestCarWheelX ) 
            {
        	currentCarWheel1X -= 0.8f * getAnimationScale();
            }
     // else if the wheels is going left, and it isn't at its lowest,
        // then decrement the wheels X offset
       else if(!stillCarWheel1 & currentCarWheel1X < lowestCarWheel1X ) 
            {
        	currentCarWheel1X += 0.8f * getAnimationScale();
        	}
        
        
        
        // if the car wheels is going right, and it isn't at its highest
        // then increment the car wheels X offset
        if(stillCarWheel2 & currentCarWheel2X > highestCarWhee2X ) 
            {	
        	            currentCarWheel2X -= 0.8f * getAnimationScale();
        	}
        // else if the wheels is going left, and it isn't at its lowest,
        // then decrement the wheels X offset
        else if(!stillCarWheel2 & currentCarWheel2X < lowestCarWheel2X )
            {
    	            	currentCarWheel2X += 0.8f * getAnimationScale();
    	    }
        
        
        
        // making the wings rotate all time
        if (isStillWing & wingsRotationAngle > 360.0f) // Wrap the angle back around into 0-360 degrees.
        {          for(int i =0 ;i <1000 ;i++) {
                    wingsRotationAngle = 0.0f;
                  }
        }
        
        
        
        // if the wings is going up, and it isn't at its highest
        // then increment the wings Y offset
        if(isStillWingUp & currentWingY > highestWingY)
        {   
        	currentWingY -= 0.1f * getAnimationScale();
        }
        // else if the wings is going down, and it isn't at its lowest,
        // then decrement the wings Y offset
        else if(!isStillWingUp & currentWingY < lowestWingY)
        {   
        	currentWingY += 0.1f * getAnimationScale();
        }
        
        
        
        // if the helicopter base is going up, and it isn't at its highest
        // then increment the base Y offset
        if(isStillHeliBase & currentHeliBaseY > highestHeliBaseY)
        {  
        	currentHeliBaseY -= 0.128f * getAnimationScale(); 
        }
        // else if the base is going down, and it isn't at its lowest,
        // then decrement the base Y offset
        else if(!isStillHeliBase & currentHeliBaseY < lowestHeliBaseY)
        {         	
        	currentHeliBaseY += 0.128f * getAnimationScale();
        }
        
        
        
        // if the wings is going left, and it isn't at its highest
        // then increment the wings X offset
        if(isStillWingSide & currentWingX < highestWingX )
        {
        	currentWingX += 0.1f * getAnimationScale();
        }
     // else if the wings is going right, and it isn't at its lowest,
        // then decrement the wings X offset
       else if(!isStillWingSide & currentWingX > lowestWingX ) 
        {
        	currentWingX -= 0.1f * getAnimationScale();
        }
        
        
        
        // if the Helicopter base is going left, and it isn't at its highest
        // then increment the base X offset
        if(isStillHeliBaseSide & currentHeliBaseX < highestHeliBaseX ) 
        {
        	currentHeliBaseX += 0.13f * getAnimationScale();
        }
     // else if the base is going right, and it isn't at its lowest,
        // then decrement the base X offset
       else if(!isStillWingSide & currentHeliBaseX > lowestHeliBaseX ) 
        {
        	currentHeliBaseX -= 0.13f * getAnimationScale();
        }

        
        
    }
   
    protected void renderScene()
    {
        // draw the ground plane
        GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(0.0f,-1.0f,-10.0f);
            GL11.glScalef(25.0f, 1.0f, 20.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the back plane
        GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,skyNightTextures.getTextureID());
            
            // position, scale and draw the back plane using its display list
            GL11.glTranslatef(0.0f,4.0f,-20.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScalef(25.0f, 1.0f, 10.0f);
            GL11.glCallList(planeList);
            
            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the moon
        GL11.glPushMatrix();
        {
        	// how shiny are the front faces of the sun (specular exponent)
            float sunFrontShininess  = 2.0f;
            // specular reflection of the front faces of the sun
            float sunFrontSpecular[] = {10.1f, 10.1f, 10.0f, 10.0f};
            // diffuse reflection of the front faces of the sun
            float sunFrontDiffuse[]  = {100.0f, 100.0f, 100.0f, 100.0f};
            

            
            // set the material properties for the sun using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, sunFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(sunFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(sunFrontDiffuse));

            // position and draw the moon using a sphere quadric object
            GL11.glTranslatef(currentSphereX, currentSunMoonY, -19.0f);
            new Sphere().draw(0.5f,10,10);
        }
        GL11.glPopMatrix();
        
      //The  tree  1
        GL11.glPushMatrix();
        {
            // how shiny are the front faces of the trunk (specular exponent)
            float trunkFrontShininess  = 20.0f;
            // specular reflection of the front faces of the trunk
            float trunkFrontSpecular[] = {0.2f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the trunk
            float trunkFrontDiffuse[]  = {0.38f, 0.29f, 0.07f, 1.0f};
            
            // set the material properties for the trunk using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));

            // position the tree
            GL11.glTranslatef(-1.9f, -1.0f, -4.0f);
	        GL11.glScalef(0.5f, 0.5f, 0.5f);


            
            // draw the trunk using a cylinder quadric object. Surround the draw call with a
            // push/pop matrix pair, as the cylinder will originally be aligned with the Z axis
            // and will have to be rotated to align it along the Y axis
            GL11.glPushMatrix();
            {
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                new Cylinder().draw(0.225f, 0.225f, 1.5f, 10, 10);

            }
            GL11.glPopMatrix();

            // how shiny are the front faces of the leafy head of the tree (specular exponent)
            float headFrontShininess  = 20.0f;
            // specular reflection of the front faces of the head
            float headFrontSpecular[] = {0.1f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the head
            float headFrontDiffuse[]  = {0.0f, 0.5f, 0.0f, 1.0f};
            
            // set the material properties for the head using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));

            // position and draw the leafy head using a sphere quadric object
            GL11.glTranslatef(0.0f, 2.0f, 0.0f);
            new Sphere().draw(0.8f, 9, 10);
        }
        GL11.glPopMatrix();
        

        
        
      //The tree  2
        GL11.glPushMatrix();
        {
            // how shiny are the front faces of the trunk (specular exponent)
            float trunkFrontShininess  = 20.0f;
            // specular reflection of the front faces of the trunk
            float trunkFrontSpecular[] = {0.2f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the trunk
            float trunkFrontDiffuse[]  = {0.38f, 0.29f, 0.07f, 1.0f};
            
            // set the material properties for the trunk using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));

            // position the tree
            GL11.glTranslatef(-1.0f, -1.0f, -4.0f);
	        GL11.glScalef(0.5f, 0.5f, 0.5f);


            
            // draw the trunk using a cylinder quadric object. Surround the draw call with a
            // push/pop matrix pair, as the cylinder will originally be aligned with the Z axis
            // and will have to be rotated to align it along the Y axis
            GL11.glPushMatrix();
            {
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                new Cylinder().draw(0.225f, 0.225f, 1.5f, 10, 10);

            }
            GL11.glPopMatrix();

            // how shiny are the front faces of the leafy head of the tree (specular exponent)
            float headFrontShininess  = 20.0f;
            // specular reflection of the front faces of the head
            float headFrontSpecular[] = {0.1f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the head
            float headFrontDiffuse[]  = {0.0f, 0.5f, 0.0f, 1.0f};
            
            // set the material properties for the head using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));

            // position and draw the leafy head using a sphere quadric object
            GL11.glTranslatef(0.0f, 2.0f, 0.0f);
            new Sphere().draw(0.8f, 9, 10);
        }
        GL11.glPopMatrix();
        


       
        
        //The  tree 3
        GL11.glPushMatrix();
        {
            // how shiny are the front faces of the trunk (specular exponent)
            float trunkFrontShininess  = 20.0f;
            // specular reflection of the front faces of the trunk
            float trunkFrontSpecular[] = {0.2f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the trunk
            float trunkFrontDiffuse[]  = {0.38f, 0.29f, 0.07f, 1.0f};
            
            // set the material properties for the trunk using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));

            // position the tree
            GL11.glTranslatef(0.0f, -1.0f, -4.0f);
	        GL11.glScalef(0.5f, 0.5f, 0.5f);


            
            // draw the trunk using a cylinder quadric object. Surround the draw call with a
            // push/pop matrix pair, as the cylinder will originally be aligned with the Z axis
            // and will have to be rotated to align it along the Y axis
            GL11.glPushMatrix();
            {
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                new Cylinder().draw(0.225f, 0.225f, 1.5f, 10, 10);

            }
            GL11.glPopMatrix();

            // how shiny are the front faces of the leafy head of the tree (specular exponent)
            float headFrontShininess  = 20.0f;
            // specular reflection of the front faces of the head
            float headFrontSpecular[] = {0.1f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the head
            float headFrontDiffuse[]  = {0.0f, 0.5f, 0.0f, 1.0f};
            
            // set the material properties for the head using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));

            // position and draw the leafy head using a sphere quadric object
            GL11.glTranslatef(0.0f, 2.0f, 0.0f);
            new Sphere().draw(0.8f, 9, 10);
        }
        GL11.glPopMatrix();
        
        
      //The  tree 4
        GL11.glPushMatrix();
        {
            // how shiny are the front faces of the trunk (specular exponent)
            float trunkFrontShininess  = 20.0f;
            // specular reflection of the front faces of the trunk
            float trunkFrontSpecular[] = {0.2f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the trunk
            float trunkFrontDiffuse[]  = {0.38f, 0.29f, 0.07f, 1.0f};
            
            // set the material properties for the trunk using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));

            // position the tree
            GL11.glTranslatef(2.0f, -1.0f, -4.0f);
	        GL11.glScalef(0.5f, 0.5f, 0.5f);


            
            // draw the trunk using a cylinder quadric object. Surround the draw call with a
            // push/pop matrix pair, as the cylinder will originally be aligned with the Z axis
            // and will have to be rotated to align it along the Y axis
            GL11.glPushMatrix();
            {
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                new Cylinder().draw(0.225f, 0.225f, 1.5f, 10, 10);

            }
            GL11.glPopMatrix();

            // how shiny are the front faces of the leafy head of the tree (specular exponent)
            float headFrontShininess  = 20.0f;
            // specular reflection of the front faces of the head
            float headFrontSpecular[] = {0.1f, 0.2f, 0.1f, 1.0f};
            // diffuse reflection of the front faces of the head
            float headFrontDiffuse[]  = {0.0f, 0.5f, 0.0f, 1.0f};
            
            // set the material properties for the head using OpenGL
            GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));

            // position and draw the leafy head using a sphere quadric object
            GL11.glTranslatef(0.0f, 2.0f, 0.0f);
            new Sphere().draw(0.8f, 9, 10);
        }
        GL11.glPopMatrix();
        

        

        // draw the boat base
        GL11.glPushMatrix();
        {
            // position and scale the boat base
	        GL11.glTranslatef(currentHouseX, 0.2f, -10.0f);
	        GL11.glScalef(0.5f, 0.5f, 0.5f);


	        // rotate the boat base a little so that we can see more of it
	        GL11.glRotatef(189.0f, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the boat base
            float houseFrontSpecular[] = {0.1f, 0.1f, 0.0f, 1.0f};
            // diffuse reflection of the front faces of the boat base
            float houseFrontDiffuse[]  = {1.0f, 1.0f, 0.0f, 1.0f};
            
	        // set the material properties for the boat base using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the boat  using its display list
	        GL11.glCallList(boatBaseList);
	        
	    
        }
        GL11.glPopMatrix();
        
       
        
        // draw the box on the the boat
        GL11.glPushMatrix();
        {
            // position and scale the box of the boat
	        GL11.glTranslatef(currentBoatBoxX, 0.2f, -10.0f);
	        GL11.glScalef(0.5f, 0.5f, 0.5f);


	        // rotate the box a little so that we can see more of it
	        GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the box
            float houseFrontSpecular[] = {0.1f, 0.1f, 0.0f, 1.0f};
            // diffuse reflection of the front faces of the box
            float houseFrontDiffuse[]  = {1.0f, 1.0f, 0.0f, 1.0f};
            
	        // set the material properties for the box using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the box on the boat using its display list
	        GL11.glCallList(boatCubeList);
	        
        }
        GL11.glPopMatrix();
        
        
        
     // draw the Car base
        GL11.glPushMatrix();
        {
            // position and scale the car base
	        GL11.glTranslatef(currentCarBaseX, -0.3f, -1.2f);
	        GL11.glScalef(0.1f, 0.1f, 0.1f);


	        // rotate the car base a little so that we can see more of it
	        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the car base
            float houseFrontSpecular[] = {0.1f, 0.1f, 0.0f, 1.0f};
            // diffuse reflection of the front faces of the car base
            float houseFrontDiffuse[]  = {1.0f, 1.0f, 0.0f, 1.0f};
            
	        // set the material properties for the car base using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the car  using its display list
	        GL11.glCallList(carList);
	        
	    
        }
        GL11.glPopMatrix();
        
        //making the roof for the car
        GL11.glPushMatrix();
        {
            // position and scale the roof
	        GL11.glTranslatef(currentCarRoofX, -0.24f, -1.2f);
	        GL11.glScalef(0.1f, 0.1f, 0.1f);


	        // rotate the roof a little so that we can see more of it
	        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the roof
            float houseFrontSpecular[] = {0.1f, 0.1f, 0.0f, 1.0f};
            // diffuse reflection of the front faces of the roof
            float houseFrontDiffuse[]  = {1.0f, 1.0f, 0.0f, 1.0f};
            
	        // set the material properties for the roof using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the roof using its display list
	        GL11.glCallList(carRoofList);
	        
	    
        }
        GL11.glPopMatrix();
        
        

        //making the wheel 1 for the car
        GL11.glPushMatrix();
        {
            // position and scale wheel 1
	        GL11.glTranslatef(currentCarWheel1X, -0.3f, -1.10f);
	        GL11.glScalef(0.1f, 0.1f, 0.1f);


	        // rotate the wheel a little so that we can see more of it
	        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the wheel
            float houseFrontSpecular[] = {0.0f, 0.0f, 0.0f, 0.0f};
            // diffuse reflection of the front faces of the wheel
            float houseFrontDiffuse[]  = {0.0f, 0.0f, 0.0f, 0.0f};
            
	        // set the material properties for the wheel using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the wheel  using its display list
	        GL11.glCallList(carWheelList);
	        
	    
        }
        GL11.glPopMatrix();
        

        //making the wheel 2 for the car
        GL11.glPushMatrix();
        {
            // position and scale the wheel 2
	        GL11.glTranslatef(currentCarWheel2X, -0.3f, -1.10f);
	        GL11.glScalef(0.1f, 0.1f, 0.1f);


	        // rotate the wheel a little so that we can see more of it
	        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the wheel
            float houseFrontSpecular[] = {0.0f, 0.0f, 0.0f, 0.0f};
            // diffuse reflection of the front faces of the wheel 
            float houseFrontDiffuse[]  = {0.0f, 0.0f, 0.0f, 0.0f};
            
	        // set the material properties for the wheel using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the wheel using its display list
	        GL11.glCallList(carWheelList);
	        
	    
        }
        GL11.glPopMatrix();
        
        //making the wings for the helicopter
        GL11.glPushMatrix();
        {
        	
        	
        	// position and scale the wings
	        GL11.glTranslatef(currentWingX, currentWingY, -1.19f);
	        GL11.glScalef(0.08f, 0.08f, 0.08f);
	        
            

	        // rotate the wings a little so that we can see more of it
	        GL11.glRotatef(wingsRotationAngle, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the wings
            float houseFrontSpecular[] = {0.0f, 0.0f, 0.0f, 0.0f};
            // diffuse reflection of the front faces of the wings
            float houseFrontDiffuse[]  = {0.0f, 0.0f, 0.0f, 0.0f};
            
	        // set the material properties for the house using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the wings using its display list
	        GL11.glCallList(wingsList);
	        
	    
        }
        GL11.glPopMatrix();
        
        
      //making the COT for the helicopter
        GL11.glPushMatrix();
        {
        	// position and scale the COT

	        GL11.glTranslatef(currentWingX,currentWingY-0.03f , -1.2f);
	        GL11.glScalef(0.08f, 0.08f, 0.08f);
	        


	        // rotate the COT a little so that we can see more of it
	        GL11.glRotatef(wingsRotationAngle, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the COT
            float houseFrontSpecular[] = {0.0f, 0.0f, 0.0f, 0.0f};
            // diffuse reflection of the front faces of the COT
            float houseFrontDiffuse[]  = {0.0f, 0.0f, 0.0f, 0.0f};
            
	        // set the material properties for the COT using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the COT using its display list
	        GL11.glCallList(cotList);
	        
	    
        }
        GL11.glPopMatrix();
        
        

     // draw the heli base
        GL11.glPushMatrix();
        {
        	// position and scale the heli base
	        GL11.glTranslatef(currentHeliBaseX, currentHeliBaseY, -1.6f);
	        GL11.glScalef(0.13f, 0.13f, 0.13f);

	        // rotate the heli base a little so that we can see more of it
	        GL11.glRotatef(0f, 0.0f, 1.0f, 0.0f);
	        
	        float houseFrontShininess  = 2.0f;
            // specular reflection of the front faces of the base
            float houseFrontSpecular[] = {0.1f, 0.1f, 0.0f, 1.0f};
            // diffuse reflection of the front faces of the base
            float houseFrontDiffuse[]  = {1.0f,1.0f, 1.0f, 0.0f};
            
            
	        // set the material properties for the base using OpenGL
	        GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
	        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));


	        // draw the base of the helicopter using its display list
	        GL11.glCallList(heliBodyList);
	        
	    
        }
        GL11.glPopMatrix();
        
     
       
        
        
        
        
    }
    
    protected void cleanupScene()
    {// empty
    }
    
    private void resetAnimations()
    {
        // reset all attributes that are modified by user controls or animations
    	
        currentSunMoonY = highestSunMoonY;
        currentSphereX = highestSphereX;
        currentHouseX = highestHouseX;
         risingSunMoon = true;
         isStill = true;
         stillHouse = true;
         
         
        
        
    }
    
    /**
     * Draws a plane aligned with the X and Z axis, with its front face toward positive Y.
     *  The plane is of unit width and height, and uses the current OpenGL material settings
     *  for its appearance
     */
    private void drawUnitPlane()
    {
        Vertex v1 = new Vertex(-0.5f, 0.0f,-0.5f); // left,  back
        Vertex v2 = new Vertex( 0.5f, 0.0f,-0.5f); // right, back
        Vertex v3 = new Vertex( 0.5f, 0.0f, 0.5f); // right, front
        Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left,  front
        
        // draw the plane geometry. order the vertices so that the plane faces up
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            
            GL11.glTexCoord2f(1.0f,0.0f);
            v3.submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v2.submit();
            
            GL11.glTexCoord2f(0.0f,1.0f);
            v1.submit();
        }
        GL11.glEnd();
        
        // if the user is viewing an axis, then also draw this plane
        // using lines so that axis aligned planes can still be seen
        if(isViewingAxis())
        {
            // also disable textures when drawing as lines
            // so that the lines can be seen more clearly
            GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                v4.submit();
                v3.submit();
                v2.submit();
                v1.submit();
            }
            GL11.glEnd();
            GL11.glPopAttrib();
        }
    }
   
    /**
     * Draws a roof geometry of unit length, width and height aligned along the x axis.
     * The roof uses the current OpenGL material settings for its appearance
     */
    
    private void drawUnitCube()
    {
        // the vertices for the cube (note that all sides have a length of 1)
        Vertex v1 = new Vertex(-1.8f, -2.0f  , 0.5f);
        Vertex v2 = new Vertex(-2.0f, -1.0f  , 0.5f);
        Vertex v3 = new Vertex(-0.5f, -0.5f ,  0.5f);
        Vertex v4 = new Vertex( 2.8f,  -2.0f  , 0.5f);
        Vertex v5 = new Vertex(-1.8f, -2.0f  , -0.5f);
        Vertex v6 = new Vertex(-2.0f, -1.0f  , -0.5f);
        Vertex v7 = new Vertex( -0.5f, -0.5f ,-0.5f);
        Vertex v8 = new Vertex(2.8f, -2.0f  , -0.5f);
        Vertex v9 = new Vertex(0.5f , -0.5f , 0.5f);
        Vertex v10 = new Vertex(0.5f , -0.5f , -0.5f);
        Vertex v11 = new Vertex(3.0f , -1.0f , 0.5f);
        Vertex v12 = new Vertex(3.0f , -1.0f , -0.5f);

        
        


        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
            v2.submit();
            v3.submit();
            v9.submit();
            v11.submit();
            v4.submit();
            v1.submit();
            v2.submit();
        }
        GL11.glEnd();
        
        
        
        
        
        // draw the back face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
            v6.submit();
            v7.submit();
            v10.submit();
            v12.submit();
            v8.submit();
            v5.submit();
            v6.submit();
        }
        GL11.glEnd();

        // draw the left face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
                     

        	v2.submit();
            v6.submit();
            v5.submit();
            v1.submit();
        }
        GL11.glEnd();

        // draw the right face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
                   

            v1.submit();
            v5.submit();
            v8.submit();
            v4.submit();
        }
        GL11.glEnd();

        // draw the top face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
           
            v7.submit();
            v10.submit();
            v9.submit();
            v3.submit();
        }
        GL11.glEnd();
    }
    
    private void drawUnitCube2()
    {
        // the vertices for the cube (note that all sides have a length of 1)
    	Vertex v1 = new Vertex(-0.5f, -0.5f ,  0.5f);
        Vertex v2 = new Vertex(-0.5f, 0.5f  , 0.5f);
        Vertex v3 = new Vertex(0.5f , 0.5f  , 0.5f);
        Vertex v4 = new Vertex(0.5f , -0.5f , 0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f ,-0.5f);
        Vertex v6 = new Vertex(-0.5f, 0.5f  , -0.5f);
        Vertex v7 = new Vertex(0.5f , 0.5f  , -0.5f );
        Vertex v8 = new Vertex(0.5f , -0.5f , -0.5f);

        

      GL11.glBegin(GL11.GL_POLYGON);
      {
          
      	v3.submit();
          v2.submit();
          v1.submit();
          v4.submit();
          // draw the near face
      }
      GL11.glEnd();

     
      
   
      GL11.glBegin(GL11.GL_POLYGON);
      {
          
      	
          v2.submit();
          v6.submit();
          v5.submit();
          v1.submit();
          // draw the left face

      }	
      GL11.glEnd();
      
      

     
      GL11.glBegin(GL11.GL_POLYGON);
      {
          
         v7.submit();
         v3.submit();
         v4.submit();
         v8.submit();
         //  draw the right face

      }
      GL11.glEnd();
      
      
      

      GL11.glBegin(GL11.GL_POLYGON);
      {
          
         v7.submit();
         v6.submit();
         v2.submit();
         v3.submit();
         //  draw the top face

      }
      GL11.glEnd();
      
      
      
      
      GL11.glBegin(GL11.GL_POLYGON);
      {
          
         v4.submit();
         v1.submit();
         v5.submit();
         v8.submit();
         //  draw the Bottom face

      }
      GL11.glEnd();
      
      
      
      
      GL11.glBegin(GL11.GL_POLYGON);
      {
          
         v6.submit();
         v7.submit();
         v8.submit();
         v5.submit();
         //  draw the back face

      }
      GL11.glEnd();
      


    }
    
    private void drawUnitBaseCar() {
    	//the vertexes of the body of the car
    	
  	  Vertex v1 = new Vertex(-2.5f, -0.3f ,  0.6f);
      Vertex v2 = new Vertex(-2.5f, 0.4f  , 0.8f);
      Vertex v3 = new Vertex(2.5f , 0.4f  , 0.8f);
      Vertex v4 = new Vertex(2.5f , -0.3f , 0.6f);
      Vertex v5 = new Vertex(-2.5f, -0.3f ,-0.6f);
      Vertex v6 = new Vertex(-2.5f, 0.4f  , -0.8f);
      Vertex v7 = new Vertex(2.5f , 0.4f  , -0.8f );
      Vertex v8 = new Vertex(2.5f , -0.3f , -0.6f);
      
      GL11.glBegin(GL11.GL_POLYGON);
    {

    	v3.submit();
        v2.submit();
        v1.submit();
        v4.submit();
        // draw the near face
    }
    GL11.glEnd();

   
    
 
    GL11.glBegin(GL11.GL_POLYGON);
    {


    	
        v2.submit();
        v6.submit();
        v5.submit();
        v1.submit();
        // draw the left face

    }	
    GL11.glEnd();
    
    

   
    GL11.glBegin(GL11.GL_POLYGON);
    {

       v7.submit();
       v3.submit();
       v4.submit();
       v8.submit();
       //  draw the right face

    }
    GL11.glEnd();
    
     
    

    GL11.glBegin(GL11.GL_POLYGON);
    {

       v7.submit();
       v6.submit();
       v2.submit();
       v3.submit();
       //  draw the top face

    }
    GL11.glEnd();
    
    
    
    
    GL11.glBegin(GL11.GL_POLYGON);
    {

       v4.submit();
       v1.submit();
       v5.submit();
       v8.submit();
       //  draw the Bottom face

    }
    GL11.glEnd();
    
    
    
    
    GL11.glBegin(GL11.GL_POLYGON);
    {
        

       v6.submit();
       v7.submit();
       v8.submit();
       v5.submit();
       //  draw the back face

    }
    GL11.glEnd();
    	
    	
    }
    private void drawUnitRoofCar() {
    	
       Vertex v1 = new Vertex(-2.0f, -0.5f  ,  0.5f);
       Vertex v2 = new Vertex(-0.5f,  0.5f  ,  0.5f);
       Vertex v3 = new Vertex( 0.5f,  0.5f  ,  0.5f);
       Vertex v4 = new Vertex( 2.0f, -0.5f  ,  0.5f);
       Vertex v5 = new Vertex(-2.0f, -0.5f  , -0.5f);
       Vertex v6 = new Vertex(-0.5f,  0.5f  , -0.5f);
       Vertex v7 = new Vertex( 0.5f,  0.5f  , -0.5f);
       Vertex v8 = new Vertex( 2.0f, -0.5f  , -0.5f);
       
       
       GL11.glBegin(GL11.GL_POLYGON);
     {
        
     	  v3.submit();
         v2.submit();
         v1.submit();
         v4.submit();
         // draw the near face
     }
     GL11.glEnd();

    
     
  
     GL11.glBegin(GL11.GL_POLYGON);
     {
        
     	
         v2.submit();
         v6.submit();
         v5.submit();
         v1.submit();
         // draw the left face

     }	
     GL11.glEnd();
     
     

    
     GL11.glBegin(GL11.GL_POLYGON);
     {
        

        v7.submit();
        v3.submit();
        v4.submit();
        v8.submit();
        //  draw the right face

     }
     GL11.glEnd();
     
     
     

     GL11.glBegin(GL11.GL_POLYGON);
     {
         
        v7.submit();
        v6.submit();
        v2.submit();
        v3.submit();
        //  draw the top face

     }
     GL11.glEnd();
     
     
     
     
     GL11.glBegin(GL11.GL_POLYGON);
     {
         
        v4.submit();
        v1.submit();
        v5.submit();
        v8.submit();
        //  draw the Bottom face

     }
     GL11.glEnd();
     
     
     
     
     GL11.glBegin(GL11.GL_POLYGON);
     {
         

        v6.submit();
        v7.submit();
        v8.submit();
        v5.submit();
        //  draw the back face

     }
     GL11.glEnd();
       
    }
    private void drawUnitWheel() {
      //the vertexes of wheel 1(hex 1)
  	 Vertex v1 = new Vertex(-2.0f, -0.3f ,  0.6f);
  	 Vertex v2 = new Vertex(-1.2f, -0.3f ,  0.6f);
  	 Vertex v3 = new Vertex(-1.8f,  0.0f ,  0.6f);
  	 Vertex v4 = new Vertex(-1.4f,  0.0f ,  0.6f);
  	 Vertex v5 = new Vertex(-1.8f, -0.6f ,  0.6f);
  	 Vertex v6 = new Vertex(-1.4f, -0.6f ,  0.6f);
  	
  	  //the vertexes of wheel 1(hex 2)
  	 Vertex v7 = new Vertex(-2.0f, -0.3f ,  0.7f);
  	 Vertex v8 = new Vertex(-1.2f, -0.3f ,  0.7f);
  	 Vertex v9 = new Vertex(-1.8f, 0.0f ,  0.7f);
  	 Vertex v10 = new Vertex(-1.4f, 0.0f ,  0.7f);
  	 Vertex v11 = new Vertex(-1.8f, -0.6f ,  0.7f);
  	 Vertex v12 = new Vertex(-1.4f, -0.6f ,  0.7f);
    
   GL11.glBegin(GL11.GL_POLYGON);
   {

      //  draw hex 1
         v1.submit();
         v3.submit();
         v4.submit();
         v2.submit();
         v6.submit();
         v5.submit();

         
   }
   GL11.glEnd();
   
   GL11.glBegin(GL11.GL_POLYGON);
   {

      v7.submit();
      v9.submit();
      v10.submit();
      v8.submit();
      v12.submit();
      v11.submit();
      //  draw hex 2

   }
  GL11.glEnd();
   
   GL11.glBegin(GL11.GL_POLYGON);
   {

      //  draw joining
         v1.submit();
         v7.submit();
         v9.submit();
         v3.submit();
         v4.submit();
         v10.submit();
         v8.submit();
         v2.submit();
         v6.submit();
         v12.submit();
         v11.submit();
         v5.submit();
         v1.submit();


         
   }
   GL11.glEnd();
   
   GL11.glBegin(GL11.GL_POLYGON);
   {


      //  draw joining
         v1.submit();
         v7.submit();
         v11.submit();
         v5.submit();
         v6.submit();
         v12.submit();
         v8.submit();
         v2.submit();
         v4.submit();
         v10.submit();
         v9.submit();
         v3.submit();
         v1.submit();

         
   }
   GL11.glEnd();
   
   
  	 
    }
    
    private void drawUnitWing() {
    	//the vertexes of the body of the WINGS
    	Vertex v1 = new Vertex(-2.0f, -0.05f ,  0.2f);
        Vertex v2 = new Vertex(-2.0f,  0.05f ,  0.2f);
        Vertex v3 = new Vertex(2.0f ,  0.05f ,  0.2f);
        Vertex v4 = new Vertex(2.0f , -0.05f ,  0.2f);
        Vertex v5 = new Vertex(-2.0f, -0.05f ,- 0.2f);
        Vertex v6 = new Vertex(-2.0f,  0.05f , -0.2f);
        Vertex v7 = new Vertex(2.0f ,  0.05f , -0.2f);
        Vertex v8 = new Vertex(2.0f , -0.05f , -0.2f);
        
        

        
           GL11.glBegin(GL11.GL_POLYGON);
         {
             
         	v3.submit();
             v2.submit();
             v1.submit();
             v4.submit();
             // draw the near face
         }
         GL11.glEnd();

        
         
      
         GL11.glBegin(GL11.GL_POLYGON);
         {
             

         	
             v2.submit();
             v6.submit();
             v5.submit();
             v1.submit();
             // draw the left face

         }	
         GL11.glEnd();
         
         

        
         GL11.glBegin(GL11.GL_POLYGON);
         {
             

            v7.submit();
            v3.submit();
            v4.submit();
            v8.submit();
            //  draw the right face

         }
         GL11.glEnd();
         
         
         

         GL11.glBegin(GL11.GL_POLYGON);
         {
            

            v7.submit();
            v6.submit();
            v2.submit();
            v3.submit();
            //  draw the top face

         }
         GL11.glEnd();
         
         
         
         
         GL11.glBegin(GL11.GL_POLYGON);
         {
             

            v4.submit();
            v1.submit();
            v5.submit();
            v8.submit();
            //  draw the Bottom face

         }
         GL11.glEnd();
         
         
         
         
         GL11.glBegin(GL11.GL_POLYGON);
         {
             

            v6.submit();
            v7.submit();
            v8.submit();
            v5.submit();
            //  draw the back face

         }
         GL11.glEnd();
           
           
    }
    private void drawUnitConectorTop() {
    	//CONECTOR ON TOP
        Vertex v1 = new Vertex(-0.1f, -0.4f ,  0.1f);
        Vertex v2 = new Vertex(-0.1f,  0.4f ,  0.1f);
        Vertex v3 = new Vertex(0.1f ,  0.4f ,  0.1f);
        Vertex v4 = new Vertex(0.1f , -0.4f ,  0.1f);
        Vertex v5 = new Vertex(-0.1f, -0.4f ,- 0.1f);
        Vertex v6 = new Vertex(-0.1f,  0.4f , -0.1f);
        Vertex v7 = new Vertex(0.1f ,  0.4f , -0.1f);
        Vertex v8 = new Vertex(0.1f , -0.4f , -0.1f);
        
        GL11.glBegin(GL11.GL_POLYGON);
    {
 
                v3.submit();
                v2.submit();
                v1.submit();
                v4.submit();
            // draw the near face
         }
           GL11.glEnd();




 GL11.glBegin(GL11.GL_POLYGON);
 {
 


v2.submit();
v6.submit();
v5.submit();
v1.submit();
//draw the left face

}	
GL11.glEnd();




GL11.glBegin(GL11.GL_POLYGON);
{


v7.submit();
v3.submit();
v4.submit();
v8.submit();
// draw the right face

}
GL11.glEnd();




GL11.glBegin(GL11.GL_POLYGON);
{

v7.submit();
v6.submit();
v2.submit();
v3.submit();
// draw the top face

}
GL11.glEnd();




GL11.glBegin(GL11.GL_POLYGON);
{


v4.submit();
v1.submit();
v5.submit();
v8.submit();
// draw the Bottom face

}
GL11.glEnd();




                      GL11.glBegin(GL11.GL_POLYGON);
                        {


                        v6.submit();
                        v7.submit();
                        v8.submit();
                        v5.submit();
                   // draw the back face

                    }
           GL11.glEnd();
    }
    
private void drawUnitBodyHelicopter() {
    	
    	//the vertexes of the body of the Helicopter
	 Vertex v1 = new Vertex(-1.7f, -0.5f ,  0.5f);
     Vertex v2 = new Vertex(-0.6f,  0.5f ,  0.5f);
     Vertex v3 = new Vertex(0.9f ,  0.5f ,  0.5f);
     Vertex v4 = new Vertex(0.9f , -0.5f ,  0.5f);
     Vertex v5 = new Vertex(-1.7f, -0.5f ,- 0.5f);
     Vertex v6 = new Vertex(-0.6f,  0.5f , -0.5f);
     Vertex v7 = new Vertex(0.9f ,  0.5f , -0.5f);
     Vertex v8 = new Vertex(0.9f , -0.5f , -0.5f);
     
    GL11.glBegin(GL11.GL_POLYGON);
    {
    	v3.submit();
        v2.submit();
        v1.submit();
        v4.submit();
        // draw the near face
    }
    GL11.glEnd();

   
    
 
    GL11.glBegin(GL11.GL_POLYGON);
    {

    	
        v2.submit();
        v6.submit();
        v5.submit();
        v1.submit();
        // draw the left face

    }	
    GL11.glEnd();
    
    

   
    GL11.glBegin(GL11.GL_POLYGON);
    {

       v7.submit();
       v3.submit();
       v4.submit();
       v8.submit();
       //  draw the right face

    }
    GL11.glEnd();
    
    
    

    GL11.glBegin(GL11.GL_POLYGON);
    {

       v7.submit();
       v6.submit();
       v2.submit();
       v3.submit();
       //  draw the top face

    }
    GL11.glEnd();
    
    
    
    
    GL11.glBegin(GL11.GL_POLYGON);
    {

       v4.submit();
       v1.submit();
       v5.submit();
       v8.submit();
       //  draw the Bottom face

    }
    GL11.glEnd();
    
    
    
    
    GL11.glBegin(GL11.GL_POLYGON);
    {

       v6.submit();
       v7.submit();
       v8.submit();
       v5.submit();
       //  draw the back face

    }
    GL11.glEnd();
    }
        
        
        
       
   
}
