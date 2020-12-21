import React from 'react';
import Fridge from './Fridge';
import Results from './Results';
import {Grid} from 'semantic-ui-react'

class Service extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 
            ingredients: [],
            results: [],
        };
    }

    render() {
        return (
          <div>
            <Grid columns={2} relaxed='very'>
              <Grid.Column>
                <Fridge />
              </Grid.Column>
              <Grid.Column>
                <Results />
              </Grid.Column>
            </Grid>
          </div>
        );
      }
}
export default Service;