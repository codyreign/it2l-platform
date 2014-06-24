package com.hibernate.dto;

// Generated 24-Jun-2014 16:58:48 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Exercises generated by hbm2java
 */
@Entity
@Table(name = "exercises", catalog = "italk2learn")
public class Exercises implements java.io.Serializable {

	private int idExercise;
	private String exercise;
	private String view;
	private String description;
	private String flTask;
	private Set<Ctatexercise> ctatexercises = new HashSet<Ctatexercise>(0);
	private Set<Whizzexercise> whizzexercises = new HashSet<Whizzexercise>(0);
	private Set<Flexercise> flexercises = new HashSet<Flexercise>(0);
	private Set<Sequence> sequences = new HashSet<Sequence>(0);

	public Exercises() {
	}

	public Exercises(int idExercise) {
		this.idExercise = idExercise;
	}

	public Exercises(int idExercise, String exercise, String view,
			String description, String flTask, Set<Ctatexercise> ctatexercises,
			Set<Whizzexercise> whizzexercises, Set<Flexercise> flexercises,
			Set<Sequence> sequences) {
		this.idExercise = idExercise;
		this.exercise = exercise;
		this.view = view;
		this.description = description;
		this.flTask = flTask;
		this.ctatexercises = ctatexercises;
		this.whizzexercises = whizzexercises;
		this.flexercises = flexercises;
		this.sequences = sequences;
	}

	@Id
	@Column(name = "id_exercise", unique = true, nullable = false)
	public int getIdExercise() {
		return this.idExercise;
	}

	public void setIdExercise(int idExercise) {
		this.idExercise = idExercise;
	}

	@Column(name = "exercise", length = 50)
	public String getExercise() {
		return this.exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	@Column(name = "view", length = 50)
	public String getView() {
		return this.view;
	}

	public void setView(String view) {
		this.view = view;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "fl_task", length = 20)
	public String getFlTask() {
		return this.flTask;
	}

	public void setFlTask(String flTask) {
		this.flTask = flTask;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exercises")
	public Set<Ctatexercise> getCtatexercises() {
		return this.ctatexercises;
	}

	public void setCtatexercises(Set<Ctatexercise> ctatexercises) {
		this.ctatexercises = ctatexercises;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exercises")
	public Set<Whizzexercise> getWhizzexercises() {
		return this.whizzexercises;
	}

	public void setWhizzexercises(Set<Whizzexercise> whizzexercises) {
		this.whizzexercises = whizzexercises;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exercises")
	public Set<Flexercise> getFlexercises() {
		return this.flexercises;
	}

	public void setFlexercises(Set<Flexercise> flexercises) {
		this.flexercises = flexercises;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exercises")
	public Set<Sequence> getSequences() {
		return this.sequences;
	}

	public void setSequences(Set<Sequence> sequences) {
		this.sequences = sequences;
	}

}
