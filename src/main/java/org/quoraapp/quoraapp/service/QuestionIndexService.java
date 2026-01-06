package org.quoraapp.quoraapp.service;

import lombok.RequiredArgsConstructor;
import org.quoraapp.quoraapp.model.Question;
import org.quoraapp.quoraapp.model.QuestionElasticDocument;
import org.quoraapp.quoraapp.repository.QuestionDocumentRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService{

    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public void createQuestionIndex(Question question) {

        QuestionElasticDocument questionDocument = QuestionElasticDocument.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();

        questionDocumentRepository.save(questionDocument);
    }
}
