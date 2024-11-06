package lk.ijse.bo.custom.impl;


import lk.ijse.bo.custom.ProgrammeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.entity.Program;
import lk.ijse.models.ProgramDTO;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeBOImpl implements ProgrammeBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PROGRAMME);
    @Override
    public boolean save(ProgramDTO programDTO) {
        return programDAO.save(new Program(programDTO.getCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public boolean update(ProgramDTO programDTO) {
        return programDAO.update( new Program(programDTO.getCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public boolean delete(ProgramDTO programDTO) {
        return programDAO.delete(new Program(programDTO.getCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public ProgramDTO get(String value) {
        Program item = programDAO.getObject(value);
        return new ProgramDTO(item.getCode(), item.getName(), item.getPrice(), item.getDuration());
    }

    @Override
    public List<String> getIds() {
        return programDAO.getIds();
    }

    @Override
    public List<ProgramDTO> getAll() {
        List<Program> all = programDAO.getAll();
        List<ProgramDTO> itemDtos = new ArrayList<>();
        for (Program item : all){
            itemDtos.add(new ProgramDTO(item.getCode(), item.getName(), item.getPrice(), item.getDuration()));
        }
        return itemDtos;
    }

    @Override
    public String getCurrentId() {
        return programDAO.getCurrentId();
    }
}
