package lk.ijse.dao;

import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }
    public enum DAOTypes{
        STUDENT,PROGRAMME,USER,PAYMENT
    }
    public SuperDAO getDAO(DAOTypes types){
       switch (types){
           case  STUDENT:
               return  new StudentDAOImpl();
           case  PROGRAMME:
               return  new ProgramDAOImpl();
           case  USER:
               return  new UserDAOImpl();
           case  PAYMENT:
               return  new PaymentDAOImpl();
           default:
               return null;
       }
    }
}
