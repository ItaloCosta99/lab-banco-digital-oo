import lombok.Getter;

@Getter
public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected final int agencia;
	protected final int numero;
	protected double saldo;
	protected double limiteEmprestimo;
	protected final Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
    }

	@Override
	public void sacar(double valor) {
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	@Override
	public void definirLimiteEmprestimo(double valor) {
		limiteEmprestimo = valor;
		System.out.printf("Limte para Emprestimo definido com sucesso!%nLimite: %.2f%n", limiteEmprestimo);
	}

	@Override
	public void solicitarEmprestimo(double valor) {
		if(limiteEmprestimo >= valor) {
			limiteEmprestimo -= valor;
			System.out.printf("Emprestimo realizado com sucesso!%nValor debitado: %.2f Limite atual: %.2f%n", valor, limiteEmprestimo);
		} else {
			System.out.printf("Operação negada. Acima do limite disponível: %.2f%n", limiteEmprestimo);
		}
	}

	protected void imprimirInfosComuns() {
		System.out.printf("Titular: %s%n", this.cliente.getNome());
		System.out.printf("Agencia: %d%n", this.agencia);
		System.out.printf("Numero: %d%n", this.numero);
		System.out.printf("Saldo: %.2f%n", this.saldo);
		System.out.printf("Limite para Emprestimo: %.2f%n", this.limiteEmprestimo);
	}
}
