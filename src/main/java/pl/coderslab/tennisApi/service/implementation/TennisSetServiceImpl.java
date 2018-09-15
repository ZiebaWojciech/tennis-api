package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.TennisGame;
import pl.coderslab.tennisApi.entity.TennisSet;
import pl.coderslab.tennisApi.repository.TennisGameRepository;
import pl.coderslab.tennisApi.repository.TennisSetRepository;
import pl.coderslab.tennisApi.service.TennisGameService;
import pl.coderslab.tennisApi.service.TennisSetService;

import java.util.List;

@Service
public class TennisSetServiceImpl implements TennisSetService {
    final
    TennisSetRepository tennisSetRepository;

    @Autowired
    public TennisSetServiceImpl(TennisSetRepository tennisSetRepository) {
        this.tennisSetRepository = tennisSetRepository;
    }

    @Override
    public TennisSet getOne(int id) {
        return tennisSetRepository.getOne(id);
    }

    @Override
    public List<TennisSet> getAll() {
        return tennisSetRepository.findAll();
    }

    @Override
    public TennisSet save(TennisSet tennisSet) {
        return tennisSetRepository.save(tennisSet);
    }
}
