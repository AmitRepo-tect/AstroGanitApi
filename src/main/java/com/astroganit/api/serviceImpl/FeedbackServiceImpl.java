package com.astroganit.api.serviceImpl;

import com.astroganit.api.entities.Feedback;
import com.astroganit.api.payload.FeedbackDto;
import com.astroganit.api.repository.FeedbackRepo;
import com.astroganit.api.service.FeedbackService;
import java.util.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
   @Autowired
   private ModelMapper mm;
   @Autowired
   private FeedbackRepo feedbackRepo;

   public FeedbackDto createFeedBack(FeedbackDto feedbackDto) {
      Feedback feedback = (Feedback)this.mm.map(feedbackDto, Feedback.class);
      feedback.setCreatedDate(new Date());
      Feedback saveFeedback = (Feedback)this.feedbackRepo.save(feedback);
      return (FeedbackDto)this.mm.map(saveFeedback, FeedbackDto.class);
   }
}
