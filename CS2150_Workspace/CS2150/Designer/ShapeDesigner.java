package Designer;

import org.lwjgl.opengl.GL11;

import GraphicsLab.Colour;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * The shape designer is a utility class which assits you with the design of 
 * a new 3D object. Replace the content of the drawUnitShape() method with
 * your own code to creates vertices and draw the faces of your object.
 * 
 * You can use the following keys to change the view:
 *   - TAB		switch between vertex, wireframe and full polygon modes
 *   - UP		move the shape away from the viewer
 *   - DOWN     move the shape closer to the viewer
 *   - X        rotate the camera around the x-axis (clockwise)
 *   - Y or C   rotate the camera around the y-axis (clockwise)
 *   - Z        rotate the camera around the z-axis (clockwise)
 *   - SHIFT    keep pressed when rotating to spin anti-clockwise
 *   - A 		Toggle colour (only if using submitNextColour() to specify colour)
 *   - SPACE	reset the view to its initial settings
 *  
 * @author Remi Barillec
 *
 */
public class ShapeDesigner extends AbstractDesigner {
	
	/** Main method **/
	public static void main(String args[])
    {   
		new ShapeDesigner().run( WINDOWED, "Designer", 0.01f);
    }
	
	/** Draw the shape **/
    protected void drawUnitShape()
    {
    	Vertex v1 = new Vertex(-0.5f, -0.5f ,  0.5f);
        Vertex v2 = new Vertex(-0.5f, 0.5f  , 0.5f);
        Vertex v3 = new Vertex(0.5f , 0.5f  , 0.5f);
        Vertex v4 = new Vertex(0.5f , -0.5f , 0.5f);
        Vertex v5 = new Vertex(-0.5f, -0.5f ,-0.5f);
        Vertex v6 = new Vertex(-0.5f, 0.5f  , -0.5f);
        Vertex v7 = new Vertex(0.5f , 0.5f  , -0.5f );
        Vertex v8 = new Vertex(0.5f , -0.5f , -0.5f);
        Vertex v9 = new Vertex(3.0f , -1.0f , 0.5f);
        Vertex v10 = new Vertex(3.0f , -1.0f , -0.5f);
        Vertex v11 = new Vertex(2.8f,  -2.0f  , 0.5f);
        Vertex v12 = new Vertex(2.8f, -2.0f  , -0.5f);
        Vertex v13 = new Vertex(1.0f, -0.5f  , 0.5f);
        Vertex v14 = new Vertex(1.0f, -0.5f  , -0.5f);
        Vertex v15 = new Vertex(-2.0f, -1.0f  , 0.5f);
        Vertex v16 = new Vertex(-2.0f, -1.0f  , -0.5f);
        Vertex v17 = new Vertex(-1.8f, -2.0f  , 0.5f);
        Vertex v18 = new Vertex(-1.8f, -2.0f  , -0.5f);




        



        


        
        
        GL11.glBegin(GL11.GL_POINTS);
        
        v1.submit();
        v2.submit();
        v3.submit();
        v4.submit();
        v5.submit();
        v6.submit();
        
        v9.submit();
        v10.submit();
        v11.submit();
        v12.submit();
        
        
        
        v13.submit();
        v14.submit();
        
        v15.submit();
        v16.submit();
        v17.submit();
        v18.submit();
        
        
       
        
        GL11.glEnd();
       

      
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
        
        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v4.submit();
           v3.submit();
           v13.submit();
           
           //  draw the triangle face

        }
        GL11.glEnd();
        
        
     
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v8.submit();
           v7.submit();
           v14.submit();
           
           //  draw the triangle face 2

        }
        GL11.glEnd();
     
        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v4.submit();
           v8.submit();
           v14.submit();
           v13.submit();
           
           //  draw the triangle face bottom

        }
        GL11.glEnd();
        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v9.submit();
           v10.submit();
           v12.submit();
           v11.submit();
           
           //  draw the square face bottom

        }
        GL11.glEnd();
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v13.submit();
           v9.submit();
           v10.submit();
           v14.submit();
           
           //  draw the another face 

        }
        GL11.glEnd();
        
        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v15.submit();
           v16.submit();
           v18.submit();
           v17.submit();
           
           //  draw the behind face 

        }
        GL11.glEnd();
        
        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v15.submit();
           v1.submit();
           v5.submit();
           v16.submit();
           
           //  draw the back back face 

        }
        GL11.glEnd();
        
        
        
        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v15.submit();
           v9.submit();
           v10.submit();
           v16.submit();
           
           //  draw the face down face 

        }
        GL11.glEnd();
        
        
        
        
        GL11.glBegin(GL11.GL_POLYGON);
        {
            
           v17.submit();
           v11.submit();
           v12.submit();
           v18.submit();
           
           //  draw the face down face 2

        }
        GL11.glEnd();
        

        
     
        
       

		}
    
		/*
		//back of the laser
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v9.toVector(), v3.toVector(),
					v11.toVector()).submit();



			v1.submit();
			v9.submit();
			v3.submit();
			v11.submit();
			v7.submit();
			v15.submit();

			GL11.glEnd();

		}

		//bottom of the laser
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v11.toVector(), v4.toVector(),
					v12.toVector()).submit();

			v3.submit();
			v11.submit();
			v4.submit();
			v12.submit();
			v5.submit();
			v13.submit();
			v15.submit();
			v7.submit();

			GL11.glEnd();

		}

		//nozzle of the laser
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v10.toVector(), v4.toVector(),
					v12.toVector()).submit();

			v2.submit();
			v10.submit();
			v4.submit();
			v12.submit();

			GL11.glEnd();

		}

		//handle of the laser
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v14.toVector(), v8.toVector(),
					v16.toVector()).submit();

			v6.submit();
			v14.submit();
			v8.submit();
			v16.submit();

			GL11.glEnd();

		}        
		*/
    
}