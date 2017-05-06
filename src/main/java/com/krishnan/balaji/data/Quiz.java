package com.krishnan.balaji.data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.krishnan.balaji.data.xml.LocalDateTimeAdapter;

@XmlRootElement(namespace = "http://com.krishnan.balaji.data/quiz")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder={"difficulty","quizDuration","quizStartTime","questions"})
public class Quiz {

	private long id;
	private LocalDateTime quizStartTime;
	private Duration quizDuration;
	private Difficulty difficulty;
	private Set<Question> questions;

	@XmlAttribute
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
	public LocalDateTime getQuizStartTime() {
		return quizStartTime;
	}

	public void setQuizStartTime(LocalDateTime quizStartTime) {
		this.quizStartTime = quizStartTime;
	}

	public Duration getQuizDuration() {
		return quizDuration;
	}

	public void setQuizDuration(Duration quizDuration) {
		this.quizDuration = quizDuration;
	}

	@XmlElement
	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	@XmlElementWrapper(name = "questions")
	@XmlElement(name = "question")
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}