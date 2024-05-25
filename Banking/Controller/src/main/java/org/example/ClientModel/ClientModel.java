package org.example.ClientModel;

import lombok.Getter;
import org.example.ModelAbstractions.IClientModel;
import org.example.ModelAbstractions.IClientModelBuilder;

import java.util.Objects;

public class ClientModel implements IClientModel {
    private ClientModel(String name, String secondName, String address, String passport){
        _name=name;
        _secondName=secondName;
        _address=address;
        _passport=passport;
    }
    @Getter
    private String _name;
    @Getter
    private String _secondName;
    private String _address;
    private String _passport;

    @Override
    public void SetPassport(String passport) {
        _passport=passport;
    }

    @Override
    public void SetAddress(String address) {
        _address=address;
    }

    @Override
    public ClientStatus GetClientStatus() {
        if (Objects.equals(_address, "") || Objects.equals(_passport, "")){
            return ClientStatus.untrustable;
        }
        return ClientStatus.trustable;
    }

    public static class ClientModelBuilder implements IClientModelBuilder{
        public ClientModelBuilder(String name, String secondName){
            _name = name;
            _secondName = secondName;
        }
        private String _name;
        private String _secondName;
        private String _address="";
        private String _passport="";
        @Override
        public IClientModel Create() {
            return new ClientModel(_name,_secondName,_address,_passport);
        }

        @Override
        public IClientModelBuilder WithPassport(String passport) {
            _passport = passport;
            return this;
        }

        @Override
        public IClientModelBuilder WithAddress(String Address) {
            _address = Address;
            return this;
        }
    }
}
