package org.example.BanksModel;

import lombok.Getter;
import org.example.AccountsModel.AccountOperationResult;
import org.example.ModelAbstractions.IAccountController;
import org.example.ModelAbstractions.IBankController;
import org.example.ModelAbstractions.ITransaction;

public class TransferTransaction implements ITransaction {
    private IAccountController _accountId;
    private IAccountController _toAccountId;
    private float _amount;
    private Boolean _isActivated=false;
    @Getter
    private int _id;
    @Getter
    private Boolean local=true;

    public TransferTransaction(IAccountController accountId, IAccountController toAccountId, float amount,int id){
        _accountId = accountId;
        _toAccountId = toAccountId;
        _amount=amount;
        _id=id;
    }
    @Override
    public TransactionResult Do() {
        if (_isActivated){
            return TransactionResult.Failure;
        }
        if(_accountId.RemoveMoney(_amount) == AccountOperationResult.failure){
            return TransactionResult.Failure;
        }
        if(_toAccountId.AddMoney(_amount) ==AccountOperationResult.failure){
            _accountId.AddMoney(_amount);
            return TransactionResult.Failure;
        }
        _isActivated=true;
        return TransactionResult.Success;

    }

    @Override
    public ITransaction GetUndo() {
        return new TransferTransaction(_toAccountId,_accountId,_amount,_id);
    }
}