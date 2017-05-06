package com.krishnan.balaji.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) {
		Quiz quiz = createQuiz();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Quiz.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(quiz, new FileOutputStream(new File("quiz.xsd")));
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		 try {
	            //mapper.writeValue(new File("quiz.json"), quiz);
	            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("result.json"), quiz);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}

	private static Quiz createQuiz() {
		Quiz quiz = new Quiz();
		quiz.setDifficulty(Difficulty.EASY);
		quiz.setId(1);
		quiz.setQuizDuration(Duration.ofMinutes(23));
		quiz.setQuizStartTime(LocalDateTime.now());
		quiz.setQuestions(generateQuestions(quiz,4));
		return quiz;
	}

	private static Set<Question> generateQuestions(Quiz quiz, int count) {
		Set<Question> questions = new HashSet<>();
		for(int i=0;i<count;i++){
			Question question = new Question();
			question.setId(i);
			question.setAssociatedWith(quiz);
			question.setContent("This is question "+i);
			question.setAnswers(generateAnswers());
			question.setMultiChoice(true);
			if(question.isMultiChoice())
				question.setCorrectAnswers(pickAnswers(3,question.getAnswers()));
			else
				question.setCorrectAnswers(pickAnswers(1,question.getAnswers()));
			questions.add(question);
		}
		return questions;
	}

	private static Set<Answer> pickAnswers(int correctCount, List<Answer> answers) {
		Set<Answer> correctAnswers = new HashSet<>();
		for(int i=0;i<correctCount;i++){
			correctAnswers.add(answers.get(((int)Math.random()*answers.size())));
		}
		return correctAnswers;
	}

	private static List<Answer> generateAnswers() {
		List<Answer> answers = new ArrayList<>();
		for(int i=0;i<4;i++){
			Answer answer = new Answer();
			answer.setContent("This is answer "+i);
			answers.add(answer);
		}
		return answers;
	}
}