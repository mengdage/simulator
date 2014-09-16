package gwu.csci.arc.isa;

import gwu.csci.arc.CPU;

public class LDX extends ISA{

	public LDX(CPU cpu) {
		super(cpu);
		// TODO Auto-generated constructor stub
	}

	@Override
	int execute() {
		// TODO Auto-generated method stub
		isaCpu.A2X();
		//increase the PC by 18 which is the length of an Instruction
		isaCpu.readPC(isaPC, isaPC.length);
		isaCpu.addition(isaPC, oneInstranceLength, isaCpu.getNewPC());
		return 0;
	}

	@Override
	int pcUpdate() {
		// TODO Auto-generated method stub
		isaCpu.pcUpdate();
		return 0;
	}
}
