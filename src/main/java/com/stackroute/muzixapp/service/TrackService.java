package com.stackroute.muzixapp.service;


import java.util.List;

import com.stackroute.muzixapp.model.Track;

public interface TrackService {

	public boolean saveTrack(Track track);

	public boolean deleteTrack(int id);

	public List<Track> getAllTracks();

	public Track getTrackById(int id);

	public boolean updateTrack(Track track);

}