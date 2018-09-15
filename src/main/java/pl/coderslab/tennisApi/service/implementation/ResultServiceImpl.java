package pl.coderslab.tennisApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Player;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.repository.PlayerRepository;
import pl.coderslab.tennisApi.repository.ResultRepository;
import pl.coderslab.tennisApi.service.PlayerService;
import pl.coderslab.tennisApi.service.ResultService;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    final
    ResultRepository resultRepository;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public Result getOne(int id) {
        return resultRepository.getOne(id);
    }

    @Override
    public List<Result> getAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }
}
