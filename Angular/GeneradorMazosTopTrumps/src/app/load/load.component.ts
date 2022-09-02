import { Component, Inject, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-load',
  templateUrl: './load.component.html',
  styleUrls: ['./load.component.css']
})
export class LoadComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<LoadComponent>,  @Inject(MAT_DIALOG_DATA) public mensaje: string) {
      dialogRef.disableClose=true;
    }

  ngOnInit() {
  }

  
}
