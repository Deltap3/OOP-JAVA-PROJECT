/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javax.imageio.ImageIO;
/**
 *
 * @author davidzhong
 */
public class Movie {
    
    private String title;
    private String genre;
    private String release_date;
    private int run_time_minute;
    private BufferedImage image;

    public Movie(){}
    public Movie(String title, String genre, String release_date, int run_time_minute, String image_name) {
        this.title = title;
        this.genre = genre;
        this.release_date = release_date;
        this.run_time_minute = run_time_minute;
        
        try{
        this.image= ImageIO.read(new File("images/"+image_name));
        }
        catch (IOException e) {
            System.out.println("couldn't load image "+image_name);
        }
    }
    
    public void setTitle(String m_title)
    {
    this.title = m_title;
    }
    public String getTitle()
    {
    return this.title;
    }
    
    public void setGenre(String m_genre)
    {
    this.genre = m_genre;
    }
    public String getGenre()
    {
    return this.genre;
    }
    
    public void setReleaseDate(String m_release_date)
    {
        this.release_date = m_release_date;
    }
    public String getReleaseDate()
    {
        return this.release_date;
    }
    
    public void setRunTime(int m_run_time)
    {
    this.run_time_minute = m_run_time;
    }
    public int getRunTime()
    {
    return this.run_time_minute;
    }
    
    public void setImage(BufferedImage m_image)
    {
        this.image=m_image;
    }
    public BufferedImage getImage()
    {
        return this.image;
    }
    
    
    
}
