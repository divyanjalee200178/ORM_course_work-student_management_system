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
        STUDENT,PROGRAMME,USER,PAYMENT
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
            default:
                return null;
        }
    }
}
