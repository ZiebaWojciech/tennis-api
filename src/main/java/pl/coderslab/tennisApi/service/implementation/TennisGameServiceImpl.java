package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.entity.TennisGame;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.repository.TennisGameRepository;
import pl.coderslab.tennisApi.service.ResultService;
import pl.coderslab.tennisApi.service.TennisGameService;

import java.util.List;

@Service
public class TennisGameServiceImpl implements TennisGameService {
    final
    TennisGameRepository tennisGameRepository;

    @Autowired
    public TennisGameServiceImpl(TennisGameRepository tennisGameRepository) {
        this.tennisGameRepository = tennisGameRepository;
    }

    @Override
    public TennisGame getOne(int id) {
        return tennisGameRepository.getOne(id);
    }

    @Override
    public List<TennisGame> getAll() {
        return tennisGameRepository.findAll();
    }

    @Override
    public TennisGame save(TennisGame tennisGame) {
        return tennisGameRepository.save(tennisGame);
    }
}
