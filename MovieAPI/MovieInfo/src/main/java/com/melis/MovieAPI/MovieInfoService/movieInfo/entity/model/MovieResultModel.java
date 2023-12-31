package com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieResultModel implements Serializable {
    private Double rating;
    private String movieId;
    private String title;
    private String overview;
}
