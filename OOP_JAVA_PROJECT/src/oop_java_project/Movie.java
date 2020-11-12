/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_java_project;
import  java.util.GregorianCalendar;

/**
 *
 * @author davidzhong
 */
public class Movie {
    
    private int IDmovie;
    private String name;
    private String genre;
    private String director;
    GregorianCalendar releasedate;
    private int runtime_minute;

    public Movie(String name, String genre, String director, GregorianCalendar releasedate, int runtime_minute) {
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.releasedate = releasedate;
        this.runtime_minute = runtime_minute;
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
    
    public void setReleasedate(GregorianCalendar m_releasedate)
    {
    this.releasedate=m_releasedate;
    }
    public GregorianCalendar getReleasedate()
    {
    return this.releasedate;
    }
    
    
    
    
}
