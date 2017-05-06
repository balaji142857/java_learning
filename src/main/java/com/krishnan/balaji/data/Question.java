package com.krishnan.balaji.data;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(namespace = "http://com.krishnan.balaji.data/question")
@JsonIgnoreProperties(value = { "associatedWith" })
public class Question {

	private long id;
	private Quiz associatedWith;
	private List<Answer> answers;
	private String content;
	private Set<Answer> correctAnswers;
	private boolean isMultiChoice;

	@XmlAttribute
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlTransient
	public Quiz getAssociatedWith() {
		return associatedWith;
	}

	public void setAssociatedWith(Quiz associatedWith) {
		this.associatedWith = associatedWith;
	}

	@XmlElement(name="answer")
	@XmlElementWrapper(name="answers")
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isMultiChoice() {
		return isMultiChoice;
	}

	public void setMultiChoice(boolean isMultiChoice) {
		this.isMultiChoice = isMultiChoice;
	}

	@XmlElement(name="answer")
	@XmlElementWrapper(name="correct_answers")
	public Set<Answer> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(Set<Answer> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
	
}