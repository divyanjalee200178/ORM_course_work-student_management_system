package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.entity.Program;
import lk.ijse.models.ProgramDTO;


import java.util.List;

public interface ProgrammeBO extends SuperBO {
    public boolean save(ProgramDTO itemDto);
    public boolean update(ProgramDTO itemDto);
    public boolean delete(ProgramDTO itemDto);
    public ProgramDTO get(String value);

    List<String> getIds();

    List<ProgramDTO> getAll();

    String getCurrentId();


    Program getProgramByName(String programName);
    Program getProgramById(String programId) throws Exception;
    public ProgramDTO searchById(String programId);
    public List<ProgramDTO> getAllPrograms();
}
