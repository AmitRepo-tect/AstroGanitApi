package com.astroganit.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
   name = "dailyhoroscope"
)
public class DailyHoroscope {
   @Id
   @Column(
      name = "Id"
   )
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   @Column(
      name = "Sentence_Id"
   )
   private int sentenceId;
   @Column(
      name = "Sequence_No"
   )
   private int sequenceNo;
   @Column(
      name = "Sentence"
   )
   private String sentence;
   @Column(
      name = "Lang_Code"
   )
   private int langCode;

   public DailyHoroscope(int id, int sentenceId, int sequenceNo, String sentence, int langCode) {
      this.id = id;
      this.sentenceId = sentenceId;
      this.sequenceNo = sequenceNo;
      this.sentence = sentence;
      this.langCode = langCode;
   }

   public DailyHoroscope(String sentence) {
      this.sentence = sentence;
   }

   public DailyHoroscope() {
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getSentenceId() {
      return this.sentenceId;
   }

   public void setSentenceId(int sentenceId) {
      this.sentenceId = sentenceId;
   }

   public int getSequenceNo() {
      return this.sequenceNo;
   }

   public void setSequenceNo(int sequenceNo) {
      this.sequenceNo = sequenceNo;
   }

   public String getSentence() {
      return this.sentence;
   }

   public void setSentence(String sentence) {
      this.sentence = sentence;
   }

   public int getLangCode() {
      return this.langCode;
   }

   public void setLangCode(int langCode) {
      this.langCode = langCode;
   }
}
