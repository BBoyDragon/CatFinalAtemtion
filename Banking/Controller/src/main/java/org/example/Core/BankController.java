package org.example.Core;
import lombok.AllArgsConstructor;
import org.example.BanksModel.*;
import org.example.ModelAbstractions.*;
import org.example.AccountsModel.AccountOperationResult;
import org.example.AccountsModel.Pair;
import org.example.Core.Accounts.CreditAccountController;
import org.example.Core.Accounts.DebetAccountController;
import org.example.Core.Accounts.DepositAccountController;

import java.util.List;

@AllArgsConstructor
public class BankController implements IBankController{

    private IBankModel _model;


    @Override
    public void CreateDebetAccount(String name, String secondName) {
        IClientModel clientModel = _model.GetClient(name,secondName);
        IAccountController accountController = new DebetAccountController(clientModel, _model.GenerateAccountId(), _model.get_limit(),_model.get_fixedPercent());
        _model.AddAccount(accountController);
    }

    @Override
    public void CreateDepositAccount(String name, String secondName, int term) {
        IClientModel clientModel = _model.GetClient(name,secondName);
        IAccountController accountController = new DepositAccountController(clientModel, _model.GenerateAccountId(), term, _model.get_limit(),_model.get_differentPercent());
        _model.AddAccount(accountController);
    }

    @Override
    public void CreateCreditAccount(String name, String secondName) {
        IClientModel clientModel = _model.GetClient(name,secondName);
        IAccountController accountController = new CreditAccountController(clientModel, _model.GenerateAccountId(), _model.get_limit(), _model.get_debt());
        _model.AddAccount(accountController);
    }

    @Override
    public void CrateClient(IClientModelBuilder clientModelBuilder) {
        _model.AddClient(clientModelBuilder.Create());
    }

    @Override
    public BankOperationResult AddMoneyOnAccount(int id, float amount, int transactionId) {
        ITransaction addTransaction = new AddTransaction(this,id,amount, transactionId);
        if(addTransaction.Do() == TransactionResult.Failure){
            return BankOperationResult.Failure;
        }
        RegisterTransaction(addTransaction);
        return BankOperationResult.Success;
    }
    @Override
    public BankOperationResult RemoveMoneyFromAccount(int id, float amount,int transactionId) {
        ITransaction RemoveTransaction = new RemoveMoneyTransaction(this,id,amount, transactionId);
        if(RemoveTransaction.Do() == TransactionResult.Failure){
            return BankOperationResult.Failure;
        }
        RegisterTransaction(RemoveTransaction);
        return BankOperationResult.Success;
    }

    @Override
    public String get_name() {
        return _model.get_name();
    }

    @Override
    public BankOperationResult TransferMoneyBetweenAccounts(int fromId, int toId, float amount, int transactionId) {
        ITransaction RemoveTransaction = new TransferTransaction(_model.GetAccount(fromId), _model.GetAccount(toId),amount, transactionId);
        if(RemoveTransaction.Do() == TransactionResult.Failure){
            return BankOperationResult.Failure;
        }
        RegisterTransaction(RemoveTransaction);
        return BankOperationResult.Success;
    }

    @Override
    public void TimeSkip(int dayAmount) {
        _model.TimeSkip(dayAmount);
    }

    @Override
    public void UpdateLimit(float limit) {
        _model.UpdateLimit(limit);
    }

    @Override
    public void UpdateFixedPercent(float percent) {
        _model.UpdateFixedPercent(percent);
    }

    @Override
    public void UpdateDifferentPercent(List<Pair> percent) {
        _model.UpdateDifferentPercent(percent);
    }

    @Override
    public void RegisterTransaction(ITransaction transaction) {
        _model.AddTransaction(transaction);
    }

    @Override
    public void UndoTransaction(int transactionId, Boolean local) {
        ITransaction transaction=_model.GetAndRemoveTransaction(transactionId, local);
        if (transaction!= null){
            transaction.Do();
        }
    }

    @Override
    public IAccountController GetAccount(int id) {
        return _model.GetAccount(id);
    }
}
