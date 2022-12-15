package com.tilted.Interfaces;

import com.tilted.Models.ProducerDTO;

import java.util.List;

public interface IProducerService {
    ProducerDTO Create(ProducerDTO producer);
    List<ProducerDTO> GetAll();
    ProducerDTO GetById(int id);
    boolean DeleteById(int id);
}
