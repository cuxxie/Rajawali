/**
 * Copyright 2013 Dennis Ippel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package rajawali.bounds;

import rajawali.BaseObject3D;
import rajawali.Camera;
import rajawali.Geometry3D;

public interface IBoundingVolume {
	
	public static final int DEFAULT_COLOR = 0xFFFFFF00;
	
	public void calculateBounds(Geometry3D geometry);
	public void drawBoundingVolume(Camera camera, float[] vpMatrix, float[] projMatrix, float[] vMatrix, float[] mMatrix);
	public void transform(float[] matrix);
	public boolean intersectsWith(IBoundingVolume boundingVolume);
	
	public BaseObject3D getVisual();
	public void setBoundingColor(int color);
	public int getBoundingColor();
}
