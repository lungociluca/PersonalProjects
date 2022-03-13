package com.utcn.lab2.service;

import com.utcn.lab2.model.Entry;
import com.utcn.lab2.repository.IEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    IEntryRepository iEntryRepository;

    public Entry findById(Integer id) {
        Optional<Entry> user = iEntryRepository.findById(id);
        return user.orElse(null);
    }

    public Entry save(Entry entry) {
        return iEntryRepository.save(entry);
    }

    public void delete(Integer id) {
        iEntryRepository.deleteById(id);
    }

    public void update(Integer id, Entry entry) {
        Entry oldEntry = findById(id);
        oldEntry.setEntry_text(entry.getEntry_text());
        iEntryRepository.save(entry);
    }
}
