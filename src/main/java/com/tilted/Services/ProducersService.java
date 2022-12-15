package com.tilted.Services;

import com.tilted.Interfaces.IProducerService;
import com.tilted.Mappers.ProducerMapper;
import com.tilted.Models.ProducerDTO;
import com.tilted.Repositories.IProducersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProducersService implements IProducerService {

    private  final IProducersRepository producerRepository;

    public ProducersService(IProducersRepository producerRepository){
        this.producerRepository = producerRepository;
    }
    @Override
    public ProducerDTO Create(ProducerDTO producer) {
        return ProducerMapper.ToDTO(producerRepository.save(ProducerMapper.ToModel(producer)));
    }

    @Override
    public List<ProducerDTO> GetAll() {
        return producerRepository.findAll().stream().map(ProducerMapper::ToDTO).collect(Collectors.toList());
    }

    @Override
    public ProducerDTO GetById(int id) {
        return producerRepository.findById(id).map(ProducerMapper::ToDTO).orElse(null);
    }

    @Override
    public boolean DeleteById(int id) {
        if(!producerRepository.existsById(id)){
            return false;
        }
        producerRepository.deleteById(id);
        return true;
    }
}
