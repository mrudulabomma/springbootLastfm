package com.stackroute.muzixapp.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {

	private String name;

	private String duration;

	private String listeners;

	private String mbid;

	@Id
	private String url;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Streamable streamable;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Artist artist;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Image> image;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Attr attr;
}