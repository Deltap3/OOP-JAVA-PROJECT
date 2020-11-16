/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_java_project;

import java.sql.Date;
/**
 *
 * @author davidzhong
 */
public class Movie {
    
    private int IDmovie;
    private String name;
    private String genre;
    private String director;
    String release_date;
    private int run_time_minute;

    public Movie(String name, String genre, String director, String release_date, int run_time_minute) {
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.release_date = release_date;
        this.run_time_minute = run_time_minute;
    }
    
    public void setIdMovie(int m_idmovie)
    {
    this.IDmovie=m_idmovie;
    }
    public int getIdMovie()
    {
    return this.IDmovie;
    }
    
    public void setName(String m_name)
    {
    this.name=m_name;
    }
    public String getName()
    {
    return this.name;
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
    
    
    
    
}
