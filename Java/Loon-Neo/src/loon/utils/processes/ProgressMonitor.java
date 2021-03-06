package loon.utils.processes;

import loon.utils.MathUtils;
import loon.utils.ObjectMap;
import loon.utils.TArray;

public class ProgressMonitor implements ProgressListener {

	private final TArray<ProgressListener> _progressListeners = new TArray<ProgressListener>();
	private final ObjectMap<ProgressMonitor, ProgressListener> _childProgressMonitorToProgressListenerMap = new ObjectMap<ProgressMonitor, ProgressListener>();

	public ProgressMonitor() {

	}

	public ProgressMonitor(final ProgressListener p) {
		this._progressListeners.add(p);
	}

	@Override
	public void onProgressChanged(final int p) {
		final int progressListenerCount = this._progressListeners.size;
		for (int i = 0; i < progressListenerCount; i++) {
			this._progressListeners.get(i).onProgressChanged(p);
		}
	}

	public void addChildProgressMonitor(
			final ProgressMonitor childProgressMonitor,
			final int rangeFrom,
			final int rangeTo) {
		final ProgressListener childProgressMonitorListener = new ProgressListener() {
			@Override
			public void onProgressChanged(final int pss) {
				final int progress = MathUtils.mix(
						rangeFrom,
						rangeTo, (float) pss
								/ ProgressListener.PROGRESS_MAX);
				ProgressMonitor.this.onProgressChanged(progress);
			}
		};
		childProgressMonitor.addProgressListener(childProgressMonitorListener);
		this._childProgressMonitorToProgressListenerMap.put(
				childProgressMonitor, childProgressMonitorListener);
	}

	public void unChildProgressMonitor(
			final ProgressMonitor childProgressMonitor) {
		childProgressMonitor
				.removeProgressListener(this._childProgressMonitorToProgressListenerMap
						.get(childProgressMonitor));
	}

	private void addProgressListener(final ProgressListener p) {
		this._progressListeners.add(p);
	}

	private void removeProgressListener(final ProgressListener p) {
		this._progressListeners.add(p);
	}

}
