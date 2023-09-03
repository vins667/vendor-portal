export interface ICutPlanBundleMatrixBreakup {
  bundle?: string;
  bundlePcs?: number;
  startSequence?: string;
  endSequence?: string;
}

export class CutPlanBundleMatrixBreakup implements ICutPlanBundleMatrixBreakup {
  constructor(public bundle?: string, public bundlePcs?: number, public startSequence?: string, public endSequence?: string) {}
}
