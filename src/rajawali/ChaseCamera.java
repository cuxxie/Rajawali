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
package rajawali;

import rajawali.math.Quaternion;
import rajawali.math.vector.Vector3;

public class ChaseCamera extends Camera {
	protected Vector3 mCameraOffset;
	protected BaseObject3D mObjectToChase;
	protected float mSlerpFactor = .1f;
	protected float[] mRotMatrix;
	protected Vector3 mTmpVec;
	protected Quaternion mTmpOrientation;
	protected Quaternion mPreviousOrientation;
	
	public ChaseCamera() {
		this(new Vector3(0, 3, 16), .1f, null);
	}
	
	public ChaseCamera(Vector3 cameraOffset, float slerpFactor) {
		this(cameraOffset, .1f, null);
	}

	public ChaseCamera(Vector3 cameraOffset, float slerpFactor, BaseObject3D objectToChase) {
		super();
		mTmpOrientation = new Quaternion();
		mPreviousOrientation = new Quaternion();
		mTmpVec = new Vector3();
		mCameraOffset = cameraOffset;
		mObjectToChase = objectToChase;
		mSlerpFactor = slerpFactor;
		mRotMatrix = new float[16];
	}
	
	private Quaternion mTmpQuatChase=new Quaternion();
	
	public float[] getViewMatrix() {
		mPosition.setAll(mObjectToChase.getPosition());
		mTmpVec.setAll(mCameraOffset);
		
		mTmpOrientation.slerp(mPreviousOrientation, mObjectToChase.getOrientation(mTmpQuatChase), mSlerpFactor);
		mTmpOrientation.toRotationMatrix(mRotMatrix);
		mTmpVec.multiply(mRotMatrix);
		
		mPosition.add(mTmpVec);
		setLookAt(mObjectToChase.getPosition());
		
		mPreviousOrientation.setAll(mTmpOrientation);
		
		return super.getViewMatrix();
	}

	public void setCameraOffset(Vector3 offset) {
		mCameraOffset.setAll(offset);
	}
	
	public Vector3 getCameraOffset() {
		return mCameraOffset;
	}
	
	public void setSlerpFactor(float factor) {
		mSlerpFactor = factor;
	}
	
	public float getSlerpFactor() {
		return mSlerpFactor;
	}

	public BaseObject3D getObjectToChase() {
		return mObjectToChase;
	}

	public void setObjectToChase(BaseObject3D objectToChase) {
		this.mObjectToChase = objectToChase;
	}
	
	public BaseObject3D getChasedObject() {
		return mObjectToChase;
	}	
}