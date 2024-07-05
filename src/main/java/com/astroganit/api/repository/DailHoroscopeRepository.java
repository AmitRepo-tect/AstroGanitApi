package com.astroganit.api.repository;

import com.astroganit.api.entities.DailyHoroscope;
import com.astroganit.api.payload.DailyHorosocpeSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DailHoroscopeRepository extends JpaRepository<DailyHoroscope, Integer> {
   DailyHoroscope findBySequenceNoAndSentenceId(int seq, int sId);

   @Query("Select new com.astroganit.api.payload.DailyHorosocpeSentence(DH.sentence) From DailyHoroscope DH where DH.sequenceNo=:seq and DH.sentenceId=:sId and DH.langCode=:langCode")
   DailyHorosocpeSentence getAllLangSentence(int seq, int sId, int langCode);

   @Query("Select new com.astroganit.api.payload.DailyHorosocpeSentence(DH.sentence) From DailyHoroscope DH where DH.sequenceNo=:seq and DH.sentenceId=:sId")
   DailyHorosocpeSentence getSentence(int seq, int sId);
}
