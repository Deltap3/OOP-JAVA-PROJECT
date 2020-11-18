/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;
/**
 *
 * @author davidzhong
 */
public class Movie {
    
    private int IDmovie;
    private String title;
    private String genre;
    private String director;
    private String release_date;
    private int run_time_minute;
    private String image;

    public Movie(){}
    public Movie(int IDmovie, String title, String genre, String director, String release_date, int run_time_minute, String image) {
        this.IDmovie= IDmovie;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.release_date = release_date;
        this.run_time_minute = run_time_minute;
        this.image=image;
    }
    
    public void setIdMovie(int m_idmovie)
    {
    this.IDmovie=m_idmovie;
    }
    public int getIdMovie()
    {
    return this.IDmovie;
    }
    
    public void setTitle(String m_title)
    {
    this.title=m_title;
    }
    public String getTitle()
    {
    return this.title;
    }
    
    public void setGenre(String m_genre)
    {
    this.genre=m_genre;
    }
    public String getGenre()
    {
    return this.genre;
    }
    
    public void setDirector(String m_director)
    {
    this.director=m_director;
    }
    public String getDirector()
    {
    return this.director;
    }
    
    public void setReleaseDate(String m_release_date)
    {
        this.release_date=m_release_date;
    }
    public String getReleaseDate()
    {
        return this.release_date;
    }
    
    public void setRunTime(int m_run_time)
    {
    this.IDmovie=m_run_time;
    }
    public int getRunTime()
    {
    return this.run_time_minute;
    }
    
    public void setImage(String m_image)
    {
    this.image=m_image;
    }
    public String getImage()
    {
    return this.image;
    }
    
    
    
}
