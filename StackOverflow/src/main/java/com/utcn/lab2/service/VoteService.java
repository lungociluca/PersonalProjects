package com.utcn.lab2.service;

import com.utcn.lab2.model.*;
import com.utcn.lab2.repository.IVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    IVoteRepository iVoteRepository;

    @Autowired
    EntryService entryService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    private Integer getNewScore(Vote vote, Entry votedEntry, User authorOfVotedEntry) {
        Integer authorScore = authorOfVotedEntry.getScore();
        Integer voteType = vote.getVoteType();

        if(voteType == -1) {
            authorScore -= 2;
        }
        else {
            //if the vote is positive, we need to find out if the entry is a question or answer in order to give points
            boolean entryIsQuestion = true;
            List<Question> questions = questionService.findByEntryId(votedEntry.getEntry_id());
            if(questions.size() == 0)
                entryIsQuestion = false;

            if(entryIsQuestion)
                authorScore += 5;
            else
                authorScore += 10;
        }
        return authorScore;
    }

    private void updateUserScore(Vote vote, Entry votedEntry) {
        if(vote.getVoteType().equals(-1)) {
            User user = userService.findByID(vote.getUserId());
            user.setScore(user.getScore() - 1);
        }

        User authorOfVotedEntry = userService.findByID(votedEntry.getAuthor_id());
        Integer authorScore = getNewScore(vote, votedEntry, authorOfVotedEntry);

        authorOfVotedEntry.setScore(authorScore);
        userService.saveUser(authorOfVotedEntry);
    }

    private void undoVote(Integer oldVoteType, Entry entry, Integer idOfVotingUser) {
        if(oldVoteType == -1)  {
            User user = userService.findByID(idOfVotingUser);
            user.setScore(user.getScore() + 1);
        }

        User authorOfVotedEntry = userService.findByID(entry.getAuthor_id());
        Integer authorScore = authorOfVotedEntry.getScore();

        boolean entryIsQuestion = true;
        List<Question> questions = questionService.findByEntryId(entry.getEntry_id());
        if(questions.size() == 0)
            entryIsQuestion = false;

        if(oldVoteType == -1) {
            authorScore += 2;
        }
        else {
            if(entryIsQuestion)
                authorScore -= 5;
            else
                authorScore -=10;

        }
        authorOfVotedEntry.setScore(authorScore);
        userService.saveUser(authorOfVotedEntry);
    }

    public void delete(Integer id) {
        iVoteRepository.deleteById(id);
    }

    public void deleteByEntryId(Integer id) {
        List<Vote> votes = iVoteRepository.findByEntryId(id);
        for(Vote vote : votes) {
            iVoteRepository.deleteById(vote.getVote_id());
        }
    }

    public List<Vote> findByEntryId(Integer id) {
        return iVoteRepository.findByEntryId(id);
    }

    public void vote(Integer entryId, Integer voteType, Integer authorId) {
        if(voteType == 1 || voteType == -1) {
        }
        else {
            System.out.println("A vote can be only 1 or -1");
            return;
        }

        Entry entry = entryService.findById(entryId);
        if(entry.getAuthor_id().equals(authorId)) {
            System.out.println("Can't vote on your own entry");
            return;
        }

        List<Vote> votes = iVoteRepository.findByEntryIdAndUserId(entryId, authorId);

        if(votes.size() != 0) {
            System.out.println("User " + authorId + " has already voted on entry " + entryId);
            if(!voteType.equals(votes.get(0).getVoteType())) {
                System.out.println("Update vote");
                Vote vote = votes.get(0);
                vote.setVoteType(-1 * vote.getVoteType());
                iVoteRepository.save(vote);

                //when we update a vote we need to modify two times
                // 1) we need to cancel the effect that the previous vote had on the the score
                // 2) add to the score the effect of the new vote

                //cancel effect of old vote
                undoVote(-1 * vote.getVoteType(), entry, authorId);

                // add effect of new vote
                updateUserScore(vote, entry);
            }
        }
        else {
            //add new vote
            Vote vote = new Vote(null, authorId, voteType, entryId);
            iVoteRepository.save(vote);
            System.out.println("New vote added");

            updateUserScore(vote, entry);
        }

    }

    public Integer getVoteCount(Integer entryId) {
        List<Vote> votes = iVoteRepository.findByEntryId(entryId);
        Integer sum = 0;
        for(Vote vote : votes) {
            sum += vote.getVoteType();
        }
        return sum;
    }

    public int compareEntriesByVote(Entry entry1, Entry entry2) {
        Integer votesCountEntry1 = getVoteCount(entry1.getEntry_id());
        Integer votesCountEntry2 = getVoteCount(entry2.getEntry_id());
        return votesCountEntry2.compareTo(votesCountEntry1);
    }
}
