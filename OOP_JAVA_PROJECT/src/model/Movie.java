/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * ZHONG David
 * MAISTERRENA Pierre
 * DANIEL Juliette
 * ING3 TDE02
 */

//Class that manages the movies
public class Movie {
    //A movie have a title, genre, release date, run time and a poster
    private String title;
    private String genre;
    private String release_date;
    private int run_time_minute;
    private BufferedImage image;

    public Movie(){}
    
    //Its constructor initialize all the atributes with the values passed in parameters
    public Movie(String title, String genre, String release_date, int run_time_minute, String image_name) {
        this.title = title;
        this.genre = genre;
        this.release_date = release_date;
        this.run_time_minute = run_time_minute;
        
        try{
            //We try to find the corresponding image
            //If we find it we put it in "image"
            this.image= ImageIO.read(new File("images/"+image_name));
        }
        catch (IOException e) {
            //Else we display the error to the user
            System.out.println("couldn't load image "+image_name);
        }
    }
    
    //Title setter
    public void setTitle(String m_title)
    {
    this.title = m_title;
    }
    
    //Title getter
    public String getTitle()
    {
    return this.title;
    }
    
    //Genre setter
    public void setGenre(String m_genre)
    {
    this.genre = m_genre;
    }
    
    //Genre getter
    public String getGenre()
    {
    return this.genre;
    }
    
    //Release date setter
    public void setReleaseDate(String m_release_date)
    {
        this.release_date = m_release_date;
    }
    
    //Release date getter
    public String getReleaseDate()
    {
        return this.release_date;
    }
    
    //Run time setter
    public void setRunTime(int m_run_time)
    {
    this.run_time_minute = m_run_time;
    }
    
    //Run time setter
    public int getRunTime()
    {
    return this.run_time_minute;
    }
    
    //Poster setter
    public void setImage(BufferedImage m_image)
    {
        this.image=m_image;
    }
    
    //Poster getter
    public BufferedImage getImage()
    {
        return this.image;
    }
}
