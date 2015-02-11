package com.biplav.aiplanning.problems.monkcanibbal;

import java.util.Collection;
import java.util.HashSet;

import com.biplav.aiplanning.definitions.IState;

public class State implements IState {

	public int missionaries_left;
	public int cannibals_left;

	public int missionaries_right;
	public int cannibals_right;

	public boolean boat; // initial position

	public State(int missionaries_left, int canibals_left,
			int missionaries_right, int cannibals_right, boolean boat) {
		super();
		this.missionaries_left = missionaries_left;
		this.cannibals_left = canibals_left;
		this.missionaries_right = missionaries_right;
		this.cannibals_right = cannibals_right;
		this.boat = boat;
	}

	public boolean isValid() {
		if ((cannibals_right > missionaries_right && missionaries_right > 0)
				|| (cannibals_left > missionaries_left) && missionaries_left > 0)
			return false;
		return true;
	}

	@Override
	public Collection<IState> successor() {
		if (boat == true) { // On the left side
			return successorLeft();
		} else {
			return successorRight();
		}
	}

	private Collection<IState> successorLeft() {
		Collection<IState> newStates = new HashSet<IState>();
		// possible actions
		if (missionaries_left >= 2) {// 2m
			State state = new State(missionaries_left - 2, cannibals_left,
					missionaries_right + 2, cannibals_right, false);
			if (state.isValid())
				newStates.add(state);
		}
		if (cannibals_left >= 2) { // 2c
			State state = new State(missionaries_left, cannibals_left - 2,
					missionaries_right, cannibals_right + 2, false);
			if (state.isValid())
				newStates.add(state);
		}
		if (missionaries_left >= 1 && cannibals_left >= 1) { // 1m1c
			State state = new State(missionaries_left - 1, cannibals_left - 1,
					missionaries_right + 1, cannibals_right + 1, false);
			if (state.isValid())
				newStates.add(state);
		}
		if (missionaries_left >= 1) { // 1m
			State state = new State(missionaries_left - 1, cannibals_left,
					missionaries_right + 1, cannibals_right, false);
			if (state.isValid())
				newStates.add(state);
		}
		if (cannibals_left >= 1) { // 1c
			State state = new State(missionaries_left, cannibals_left - 1,
					missionaries_right, cannibals_right + 1, false);
			if (state.isValid())
				newStates.add(state);
		}
		return newStates;
	}

	// Editing this
	private Collection<IState> successorRight() {
		Collection<IState> newStates = new HashSet<IState>();
		// possible actions
		if (missionaries_right >= 2) {// 2m
			State state = new State(missionaries_left + 2, cannibals_left,
					missionaries_right - 2, cannibals_right, true);
			if (state.isValid())
				newStates.add(state);
		}
		if (cannibals_right >= 2) { // 2c
			State state = new State(missionaries_left, cannibals_left + 2,
					missionaries_right, cannibals_right - 2, true);
			if (state.isValid())
				newStates.add(state);
		}
		if (missionaries_right >= 1 && cannibals_right >= 1) { // 1m1c
			State state = new State(missionaries_left + 1, cannibals_left + 1,
					missionaries_right - 1, cannibals_right - 1, true);
			if (state.isValid())
				newStates.add(state);
		}
		if (missionaries_right >= 1) { // 1m
			State state = new State(missionaries_left + 1, cannibals_left,
					missionaries_right - 1, cannibals_right, true);
			if (state.isValid())
				newStates.add(state);
		}
		if (cannibals_right >= 1) { // 1c
			State state = new State(missionaries_left, cannibals_left + 1,
					missionaries_right, cannibals_right - 1, true);
			if (state.isValid())
				newStates.add(state);
		}
		return newStates;
	}

	@Override
	public String toString() {
		return "L->" + missionaries_left + "m" + cannibals_left + "c" + boat
				+ "boat R->" + missionaries_right + "m" + cannibals_right + "c"
				+ !boat + "boat";
	}

	@Override
	public int hashCode() {
		int code = missionaries_left * 1000000 + cannibals_left * 100000;
		if (boat)
			code = code + 10000;
		code = code + missionaries_right * 1000 + cannibals_right * 100;
		if (!boat)
			code = code + 10;
		return code;
	}

	@Override
	public boolean equals(Object obj) {
		State nxt = (State) obj;
		if (nxt.missionaries_left == missionaries_left
				&& nxt.cannibals_left == cannibals_left && nxt.boat == boat)
			return true;
		return false;
	}

}