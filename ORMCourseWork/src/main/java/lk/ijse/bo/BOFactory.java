package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;

    }
    public enum BOTypes{
        STUDENT,PROGRAMME,USER,PAYMENT,STUDENTPROGRAME
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return  new StudentBOImpl();
            case PROGRAMME:
                return  new ProgrammeBOImpl();
            case USER:
                return  new UserBOImpl();
            case PAYMENT:
                return  new PaymentBOIml();
            case STUDENTPROGRAME:
                return  new StudentProgrammeBOIml();
            default:
                return null;
        }
    }
}
