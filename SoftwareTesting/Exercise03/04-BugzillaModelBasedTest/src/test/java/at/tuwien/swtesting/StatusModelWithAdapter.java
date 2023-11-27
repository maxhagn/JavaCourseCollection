/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

import static org.junit.Assert.*;

public class StatusModelWithAdapter implements FsmModel {
    enum BugStatus {
        NEW,
        IN_PROGRESS,
        RESOLVED,
        UNCONFIRMED,
        VERIFIED;
    }

    BugStatus status;
    private BugzillaAdapter adapter;

    public StatusModelWithAdapter(BugzillaAdapter adapter) {
        this.adapter = adapter;
        reset(true);
    }

    public Object getState() {
        return status;
    }

    public void reset(boolean testing) {
		adapter.createNewBug();
		status = BugStatus.NEW;
    }

    @Action
    public void changeToNew() {
        assertTrue(adapter.performChangeToNew());
        status = BugStatus.NEW;
    }

    @Action
    public void changeToInProgress() {
		if (status != BugStatus.RESOLVED && status != BugStatus.VERIFIED) {
			assertTrue(adapter.performChangeToInProgress());
			status = BugStatus.IN_PROGRESS;
		}
    }

    @Action
    public void changeToResolved() {
        if (status != BugStatus.VERIFIED) {
            assertTrue(adapter.performChangeToResolved());
            status = BugStatus.RESOLVED;
        }
    }

	@Action
	public void changeToVerified() {
		if (status == BugStatus.RESOLVED) {
			assertTrue(adapter.performChangeToVerified());
			status = BugStatus.VERIFIED;
		}
	}

	@Action
	public void changeToUnconfirmed() {
		if (status == BugStatus.RESOLVED) {
			assertTrue(adapter.performChangeToUnconfirmed());
			status = BugStatus.UNCONFIRMED;
		}
	}
}
